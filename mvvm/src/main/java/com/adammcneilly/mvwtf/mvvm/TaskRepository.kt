package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
}
