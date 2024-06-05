package com.adammcneilly.mvwtf.mvp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adammcneilly.mvwtf.core.ErrorScreen
import com.adammcneilly.mvwtf.core.LoadingScreen
import com.adammcneilly.mvwtf.core.TaskList

class MVPMainActivity : ComponentActivity(), TaskListContract.View {
    private val presenter = TaskListPresenter(this, InMemoryTaskRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState?.getParcelable(STATE_KEY, MVPTaskListViewState::class.java)
        } else {
            @Suppress("DEPRECATION")
            savedInstanceState?.getParcelable(STATE_KEY)
        }

        if (state != null) {
            presenter.restoreState(state)
        }

        presenter.viewCreated()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(STATE_KEY, presenter.getState())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.viewDestroyed()
        super.onDestroy()
    }

    override fun render(state: MVPTaskListViewState) {
        setContent {
            if (state.isLoading) {
                LoadingScreen()
            } else if (state.error != null) {
                ErrorScreen(state.error.orEmpty())
            } else {
                TaskList(state.tasks)
            }
        }
    }

    companion object {
        private const val STATE_KEY = "state"
    }
}
