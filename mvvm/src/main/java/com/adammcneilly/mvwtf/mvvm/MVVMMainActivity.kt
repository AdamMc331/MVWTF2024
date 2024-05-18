package com.adammcneilly.mvwtf.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adammcneilly.mvwtf.core.ErrorScreen
import com.adammcneilly.mvwtf.core.LoadingScreen
import com.adammcneilly.mvwtf.core.TaskList

class MVVMMainActivity : ComponentActivity() {
    private val viewModel: TaskListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return TaskListViewModel(InMemoryTaskRepository()) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val state by viewModel.state.collectAsState()

            Render(state)
        }
    }

    @Composable
    private fun Render(state: TaskListViewModel.State) {
        if (state.isLoading) {
            LoadingScreen()
        } else if (state.error != null) {
            ErrorScreen(state.error)
        } else {
            TaskList(state.tasks)
        }
    }
}
