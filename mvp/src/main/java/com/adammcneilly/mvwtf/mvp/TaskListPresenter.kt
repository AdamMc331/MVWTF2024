package com.adammcneilly.mvwtf.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TaskListPresenter(
    private var view: TaskListContract.View?,
    private val model: TaskListContract.Model,
) : TaskListContract.Presenter {

    private val job = Job()

    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val presenterScope = CoroutineScope(coroutineContext)

    override fun viewCreated() {
        view?.showLoading()

        presenterScope.launch {
            delay(3_000)

            try {
                val tasks = model.getTasks()
                view?.showTasks(tasks)
            } catch (e: Exception) {
                view?.showError(e.message.toString())
            }
        }
    }

    override fun viewDestroyed() {
        view = null
        job.cancel()
    }
}
