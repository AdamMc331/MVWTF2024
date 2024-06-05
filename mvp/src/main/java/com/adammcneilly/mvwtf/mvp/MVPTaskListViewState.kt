package com.adammcneilly.mvwtf.mvp

import android.os.Parcelable
import com.adammcneilly.mvwtf.core.Task
import kotlinx.parcelize.Parcelize

@Parcelize
data class MVPTaskListViewState internal constructor(
    val isLoading: Boolean,
    val tasks: List<Task>,
    val error: String?,
) : Parcelable {
    companion object {
        fun loading(): MVPTaskListViewState {
            return MVPTaskListViewState(
                isLoading = true,
                tasks = emptyList(),
                error = null,
            )
        }

        fun success(tasks: List<Task>): MVPTaskListViewState {
            return MVPTaskListViewState(
                isLoading = false,
                tasks = tasks,
                error = null,
            )
        }

        fun error(error: String): MVPTaskListViewState {
            return MVPTaskListViewState(
                isLoading = false,
                tasks = emptyList(),
                error = error,
            )
        }
    }
}
