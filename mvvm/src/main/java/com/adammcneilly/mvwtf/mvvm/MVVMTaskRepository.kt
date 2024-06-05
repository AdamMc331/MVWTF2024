package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task

interface MVVMTaskRepository {
    suspend fun getTasks(): List<Task>
}
