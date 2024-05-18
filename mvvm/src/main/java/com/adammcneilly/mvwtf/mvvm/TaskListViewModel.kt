package com.adammcneilly.mvwtf.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvwtf.core.Task
import com.adammcneilly.mvwtf.core.TaskListViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskListViewModel(
    taskRepository: TaskRepository,
) : ViewModel() {
    private val mutableState = MutableStateFlow(TaskListViewState.loading())
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
        mutableState.value = TaskListViewState.loading()
    }

    private fun setTasks(tasks: List<Task>) {
        mutableState.value = TaskListViewState.success(tasks)
    }

    private fun setError(error: String) {
        mutableState.value = TaskListViewState.error(error)
    }
}
