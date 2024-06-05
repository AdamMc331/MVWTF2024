package com.adammcneilly.mvwtf.mvi

import com.adammcneilly.mvwtf.core.Task
import kotlinx.coroutines.delay

class MVIInMemoryTaskRepository : MVITaskRepository {
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
