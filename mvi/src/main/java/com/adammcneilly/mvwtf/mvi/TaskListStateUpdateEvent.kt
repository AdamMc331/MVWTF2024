package com.adammcneilly.mvwtf.mvi

import com.adammcneilly.mvwtf.core.Task

sealed class TaskListStateUpdateEvent : StateUpdateEvent {
    data object SetLoading : TaskListStateUpdateEvent()

    data class SetTasks(val tasks: List<Task>) : TaskListStateUpdateEvent()

    data class SetError(val error: String) : TaskListStateUpdateEvent()
}
