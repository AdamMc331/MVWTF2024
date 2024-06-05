package com.adammcneilly.mvwtf.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvwtf.core.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskListViewModel(
    taskRepository: TaskRepository,
) : ViewModel() {
    private val mutableState: MutableStateFlow<MVVMTaskListViewState> = MutableStateFlow(MVVMTaskListViewState.Loading)
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
