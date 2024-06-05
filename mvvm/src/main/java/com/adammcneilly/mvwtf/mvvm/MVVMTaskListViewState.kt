package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task

/**
 * A collection of possible states for our task list screen.
 */
sealed interface MVVMTaskListViewState {
    /**
     * This state indicates we have not yet fetched a list of tasks.
     *
     * Once that happens, we'll transition to [Loaded] or [Error] states.
     */
    data object Loading : MVVMTaskListViewState

    /**
     * This state represents a successful request for a list of tasks.
     *
     * @property[tasks] The list of [Task] entities we should render to the user.
     */
    data class Loaded(
        val tasks: List<Task>,
    ) : MVVMTaskListViewState

    /**
     * This state represents a failure to request a list of tasks.
     *
     * @property[error] A user friendly message indicating what failed
     * during our request.
     */
    data class Error(
        val error: String,
    ) : MVVMTaskListViewState
}
