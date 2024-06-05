package com.adammcneilly.mvwtf.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvwtf.core.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * This is our main state container for an MVVM screen. The [MVVMMainActivity] will
 * depend on this class, and it will expose a UI state via the [state] property.
 *
 * Because this class implements Android architecture [ViewModel], it will outlive
 * the activity's configuration changes, and ensure state is maintained
 * across device rotation and other changes.
 *
 * @param[taskRepository] The data source that will be used to request a list of tasks.
 */
class MVVMTaskListViewModel(
    taskRepository: MVVMTaskRepository,
) : ViewModel() {
    private val mutableState: MutableStateFlow<MVVMTaskListViewState> = MutableStateFlow(MVVMTaskListViewState.Loading)

    /**
     * Expose a state flow of our [MVVMTaskListViewState] that the UI layer
     * can observe, and render a screen based on this value.
     */
    val state = mutableState.asStateFlow()

    init {
        setLoading()

        viewModelScope.launch {
            try {
                setTasks(taskRepository.getTasks())
            } catch (e: Exception) {
                setError(e.message ?: "Something went wrong")
            }
        }
    }

    private fun setLoading() {
        mutableState.value = MVVMTaskListViewState.Loading
    }

    private fun setTasks(tasks: List<Task>) {
        mutableState.value = MVVMTaskListViewState.Loaded(tasks)
    }

    private fun setError(error: String) {
        mutableState.value = MVVMTaskListViewState.Error(error)
    }
}
