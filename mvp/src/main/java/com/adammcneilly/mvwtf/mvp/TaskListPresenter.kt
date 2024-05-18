package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.TaskListViewState
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

    private var state = TaskListViewState.loading()
        set(value) {
            field = value
            view?.render(value)
        }

    override fun viewCreated() {
        if (state.tasks.isNotEmpty()) {
            return
        }

        state = state.copy(
            isLoading = true,
        )

        presenterScope.launch {
            delay(DELAY_MS)

            try {
                val tasks = model.getTasks()
                state = state.copy(
                    isLoading = false,
                    tasks = tasks,
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message ?: "Something went wrong",
                )
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

    companion object {
        const val DELAY_MS = 3_000L
    }
}
