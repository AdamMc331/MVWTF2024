package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.Task

object MVPTaskListContract {
    interface Model {
        suspend fun getTasks(): List<Task>
    }

    interface View {
        fun render(state: MVPTaskListViewState)
    }

    interface Presenter {
        fun viewCreated()

        fun viewDestroyed()

        fun getState(): MVPTaskListViewState

        fun restoreState(state: MVPTaskListViewState)
    }
}
