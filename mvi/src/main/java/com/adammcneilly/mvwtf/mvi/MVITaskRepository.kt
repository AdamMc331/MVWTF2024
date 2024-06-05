package com.adammcneilly.mvwtf.mvi

import com.adammcneilly.mvwtf.core.Task

interface MVITaskRepository {
    suspend fun getTasks(): List<Task>
}
