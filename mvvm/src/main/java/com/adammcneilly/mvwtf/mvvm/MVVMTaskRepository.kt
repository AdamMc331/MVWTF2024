package com.adammcneilly.mvwtf.mvvm

import com.adammcneilly.mvwtf.core.Task

/**
 * This represents the model layer of our MVVM application. It defines
 * any data related requests to be made.
 */
interface MVVMTaskRepository {
    /**
     * Retrieve a list of [Task] entities that will eventually be shown to the user.
     */
    suspend fun getTasks(): List<Task>
}
