package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MVPTaskListPresenter(
    private var view: MVPTaskListContract.View?,
    private val model: MVPTaskListContract.Model,
) : MVPTaskListContract.Presenter {
    private val job = Job()

    private val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val presenterScope = CoroutineScope(coroutineContext)

    private var state = MVPTaskListViewState.loading()
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

    override fun getState(): MVPTaskListViewState {
        return state
    }

    override fun restoreState(state: MVPTaskListViewState) {
        this.state = state
    }

    private fun setLoading() {
        state = MVPTaskListViewState.loading()
    }

    private fun setTasks(tasks: List<Task>) {
        state = MVPTaskListViewState.success(tasks)
    }

    private fun setError(error: String) {
        state = MVPTaskListViewState.error(error)
    }
}
