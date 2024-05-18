package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.Task
import com.adammcneilly.mvwtf.core.TaskListViewState

object TaskListContract {
    interface Model {
        suspend fun getTasks(): List<Task>
    }

    interface View {
        fun render(state: TaskListViewState)
    }

    interface Presenter {
        fun viewCreated()

        fun viewDestroyed()

        fun getState(): TaskListViewState

        fun restoreState(state: TaskListViewState)
    }
}
