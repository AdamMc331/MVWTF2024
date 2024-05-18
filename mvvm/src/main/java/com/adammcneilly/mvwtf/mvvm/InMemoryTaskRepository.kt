package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task

class InMemoryTaskRepository : TaskRepository {
    override suspend fun getTasks(): List<Task> {
        return listOf(
            Task("Test Task 1"),
            Task("Test Task 2"),
            Task("Test Task 3"),
        )
    }
}
