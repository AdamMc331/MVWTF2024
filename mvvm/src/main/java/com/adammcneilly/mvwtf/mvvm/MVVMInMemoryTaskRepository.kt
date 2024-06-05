package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task
import kotlinx.coroutines.delay

/**
 * A simple implementation of [MVVMTaskRepository] that returns
 * a hardcoded list of tasks. There is a fake delay to demonstrate
 * the loading scenario.
 */
class MVVMInMemoryTaskRepository : MVVMTaskRepository {
    override suspend fun getTasks(): List<Task> {
        delay(DELAY_MS)

        return listOf(
            Task("Test Task 1"),
            Task("Test Task 2"),
            Task("Test Task 3"),
        )
    }

    companion object {
        private const val DELAY_MS = 3_000L
    }
}
