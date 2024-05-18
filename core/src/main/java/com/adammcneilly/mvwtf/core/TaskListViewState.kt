package com.adammcneilly.mvwtf.core

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskListViewState internal constructor(
    val isLoading: Boolean,
    val tasks: List<Task>,
    val error: String?,
) : Parcelable {
    companion object {
        fun loading(): TaskListViewState {
            return TaskListViewState(
                isLoading = true,
                tasks = emptyList(),
                error = null,
            )
        }

        fun success(tasks: List<Task>): TaskListViewState {
            return TaskListViewState(
                isLoading = false,
                tasks = tasks,
                error = null,
            )
        }

        fun error(error: String): TaskListViewState {
            return TaskListViewState(
                isLoading = false,
                tasks = emptyList(),
                error = error,
            )
        }
    }
}
