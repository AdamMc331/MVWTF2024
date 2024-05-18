package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.Task
import com.adammcneilly.mvwtf.core.TaskListViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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

    private var state = TaskListViewState.loading()
        set(value) {
            field = value
            view?.render(value)
        }

    override fun viewCreated() {
        if (state.tasks.isNotEmpty()) {
            return
        }

        setLoading()

        presenterScope.launch {
            try {
                setTasks(model.getTasks())
            } catch (e: Exception) {
                setError(e.message.toString())
            }
        }
    }

    override fun viewDestroyed() {
        view = null
        job.cancel()
    }

    override fun getState(): TaskListViewState {
        return state
    }

    override fun restoreState(state: TaskListViewState) {
        this.state = state
    }

    private fun setLoading() {
        state = TaskListViewState.loading()
    }

    private fun setTasks(tasks: List<Task>) {
        state = TaskListViewState.success(tasks)
    }

    private fun setError(error: String) {
        state = TaskListViewState.error(error)
    }
}
