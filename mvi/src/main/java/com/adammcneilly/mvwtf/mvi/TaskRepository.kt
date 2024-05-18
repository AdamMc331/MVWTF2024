package com.adammcneilly.mvwtf.mvi

import com.adammcneilly.mvwtf.core.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
}
