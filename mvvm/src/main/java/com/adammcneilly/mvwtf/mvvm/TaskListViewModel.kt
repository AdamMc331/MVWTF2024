package com.adammcneilly.mvwtf.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        mutableState.value = TaskListViewState.loading()

        viewModelScope.launch {
            try {
                val tasks = taskRepository.getTasks()
                mutableState.value = TaskListViewState.success(tasks)
            } catch (e: Exception) {
                mutableState.value = TaskListViewState.error(e.message ?: "Something went wrong")
            }
        }
    }
}
