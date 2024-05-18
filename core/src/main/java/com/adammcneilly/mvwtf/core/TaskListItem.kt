package com.adammcneilly.mvwtf.core

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskListItem(
    task: Task,
    modifier: Modifier = Modifier,
) {
    Text(
        text = task.description,
        modifier = modifier
            .padding(8.dp),
    )
}
