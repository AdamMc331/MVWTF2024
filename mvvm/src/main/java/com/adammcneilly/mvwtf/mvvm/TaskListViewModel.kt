package com.adammcneilly.mvwtf.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.mvwtf.core.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskListViewModel(
    taskRepository: TaskRepository,
) : ViewModel() {
    data class State(
        val isLoading: Boolean = true,
        val tasks: List<Task> = emptyList(),
        val error: String? = null,
    )

    private val mutableState = MutableStateFlow(State())
    val state = mutableState.asStateFlow()

    init {
        mutableState.update { currentState ->
            currentState.copy(
                isLoading = true,
            )
        }

        viewModelScope.launch {
            try {
                val tasks = taskRepository.getTasks()
                mutableState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        tasks = tasks,
                    )
                }
            } catch (e: Exception) {
                mutableState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = e.message ?: "Something went wrong",
                    )
                }
            }
        }
    }
}
