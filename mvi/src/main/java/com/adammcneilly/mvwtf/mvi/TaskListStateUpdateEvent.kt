package com.adammcneilly.mvwtf.mvi

import com.adammcneilly.mvwtf.core.Task

/**
 * A collection of [StateUpdateEvent] options that will modify the state of our
 * task list screen in an MVI architecture.
 *
 * These will be used by [MVITaskListViewModel] to push these events into a [StateMachine]
 * and modify the state value accordingly.
 */
sealed class TaskListStateUpdateEvent : StateUpdateEvent {
    /**
     * Changes the state from its current value is to a loading state.
     */
    data object SetLoading : TaskListStateUpdateEvent()

    /**
     * Changes the state from its current value to a loaded state.
     *
     * @property[tasks] The list of [Task] entities that have been fetched.
     */
    data class SetTasks(val tasks: List<Task>) : TaskListStateUpdateEvent()

    /**
     * Changes the state from its current value to an error state.
     *
     * @property[error] A user friendly string representing the value in requesting tasks.
     */
    data class SetError(val error: String) : TaskListStateUpdateEvent()
}
