package com.adammcneilly.mvwtf.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MVITaskListViewModel(
    private val taskRepository: MVITaskRepository,
) : ViewModel() {
    private val stateMachine = StateMachine<MVITaskListViewState, TaskListStateUpdateEvent, TaskListSideEffect>(
        initialState = MVITaskListViewState.Loading,
        eventProcessor = { currentState, event ->
            processEvent(currentState, event)
        },
        sideEffectProcessor = { sideEffect ->
            processSideEffect(sideEffect)
        },
    )

    val state = stateMachine.state

    init {
        stateMachine.processEvent(TaskListStateUpdateEvent.SetLoading)
    }

    private fun processSideEffect(sideEffect: TaskListSideEffect) {
        when (sideEffect) {
            TaskListSideEffect.FetchTasks -> {
                fetchTasks()
            }
        }
    }

    private fun processEvent(
        currentState: MVITaskListViewState,
        event: TaskListStateUpdateEvent,
    ): StateAndSideEffects<MVITaskListViewState, TaskListSideEffect> {
        return when (event) {
            is TaskListStateUpdateEvent.SetError -> {
                MVITaskListViewState.Error(event.error).noSideEffects()
            }

            TaskListStateUpdateEvent.SetLoading -> {
                MVITaskListViewState.Loading + TaskListSideEffect.FetchTasks
            }

            is TaskListStateUpdateEvent.SetTasks -> {
                MVITaskListViewState.Success(event.tasks).noSideEffects()
            }
        }
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                val tasks = taskRepository.getTasks()

                stateMachine.processEvent(TaskListStateUpdateEvent.SetTasks(tasks))
            } catch (e: Exception) {
                stateMachine.processEvent(TaskListStateUpdateEvent.SetError(e.message ?: "Something went wrong"))
            }
        }
    }
}
