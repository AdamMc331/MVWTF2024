package com.adammcneilly.mvwtf.mvp

import com.adammcneilly.mvwtf.core.Task

class InMemoryTaskRepository : TaskListContract.Model {
    override suspend fun getTasks(): List<Task> {
        return listOf(
            Task("Test Task 1"),
            Task("Test Task 2"),
            Task("Test Task 3"),
        )
    }
}
