package com.adammcneilly.mvwtf.mvp

import android.os.Parcelable
import com.adammcneilly.mvwtf.core.Task
import kotlinx.parcelize.Parcelize

object TaskListContract {
    interface Model {
        suspend fun getTasks(): List<Task>
    }

    interface View {
        fun render(state: State)

        @Parcelize
        data class State(
            val isLoading: Boolean = true,
            val tasks: List<Task> = emptyList(),
            val error: String? = null,
        ) : Parcelable
    }

    interface Presenter {
        fun viewCreated()
        fun viewDestroyed()

        fun getState(): View.State
        fun restoreState(state: View.State)
    }
}
