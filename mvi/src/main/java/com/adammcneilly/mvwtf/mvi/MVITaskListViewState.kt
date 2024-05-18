package com.adammcneilly.mvwtf.mvi

import com.adammcneilly.mvwtf.core.Task

sealed class MVITaskListViewState : State {
    data object Loading : MVITaskListViewState()

    data class Success(
        val tasks: List<Task>,
    ) : MVITaskListViewState()

    data class Error(
        val error: String,
    ) : MVITaskListViewState()
}
