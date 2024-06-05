package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task

sealed interface MVVMTaskListViewState {
    data object Loading : MVVMTaskListViewState

    data class Loaded(
        val tasks: List<Task>,
    ) : MVVMTaskListViewState

    data class Error(
        val error: String,
    ) : MVVMTaskListViewState
}
