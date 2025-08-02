package com.thisisnotajoke.interview.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thisisnotajoke.interview.model.MenuWeek

@Composable
fun MenuWeek(menuWeek: MenuWeek, modifier: Modifier = Modifier) {
    LazyRow {
        menuWeek.week.forEachIndexed { index, it ->
            item {
                Column(Modifier.padding(10.dp)) {
                    Text(titles[index])
                    Text(it, modifier = modifier)
                }
            }
        }
    }
}

private val titles = listOf(
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday"
)