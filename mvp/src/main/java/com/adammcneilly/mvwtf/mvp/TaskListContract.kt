package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.Task

object TaskListContract {
    interface Model {
        suspend fun getTasks(): List<Task>
    }

    interface View {
        fun showTasks(tasks: List<Task>)

        fun showLoading()

        fun showError(message: String)
    }

    interface Presenter {
        fun viewCreated()
        fun viewDestroyed()
    }
}
