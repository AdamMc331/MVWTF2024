package com.adammcneilly.mvwtf.mvp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.adammcneilly.mvwtf.core.ErrorScreen
import com.adammcneilly.mvwtf.core.LoadingScreen
import com.adammcneilly.mvwtf.core.Task
import com.adammcneilly.mvwtf.core.TaskList

class MVPMainActivity : ComponentActivity(), TaskListContract.View {

    private val presenter = TaskListPresenter(this, InMemoryTaskRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        presenter.viewCreated()
    }

    override fun onDestroy() {
        presenter.viewDestroyed()
        super.onDestroy()
    }

    override fun showTasks(tasks: List<Task>) {
        setContent {
            TaskList(tasks)
        }
    }

    override fun showLoading() {
        setContent {
            LoadingScreen()
        }
    }

    override fun showError(message: String) {
        setContent {
            ErrorScreen(message)
        }
    }
}

