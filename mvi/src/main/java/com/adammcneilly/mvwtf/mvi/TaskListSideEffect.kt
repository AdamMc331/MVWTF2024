package com.adammcneilly.mvwtf.mvi

sealed class TaskListSideEffect : SideEffect {
    data object FetchTasks : TaskListSideEffect()
}
