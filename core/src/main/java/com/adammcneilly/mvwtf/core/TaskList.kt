package com.adammcneilly.mvwtf.core

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TaskList(
    tasks: List<Task>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(tasks) { index, task ->
            TaskListItem(task)

            if (index != tasks.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}
