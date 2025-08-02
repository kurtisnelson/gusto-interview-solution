package com.thisisnotajoke.interview.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thisisnotajoke.interview.LunchMenuViewModel
import com.thisisnotajoke.interview.model.MenuState
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuList(
    modifier: Modifier = Modifier,
    viewModel: LunchMenuViewModel = viewModel(),
) {
    val response by viewModel.menu.collectAsStateWithLifecycle()
    var isRefreshing by remember { mutableStateOf(false) }
    val onRefresh: () -> Unit = {
        isRefreshing = true
        viewModel.refresh {
            isRefreshing = false
        }
    }
    PullToRefreshBox(isRefreshing, onRefresh) {
        when (response) {
            is MenuState.Loading -> {
                CircularProgressIndicator(modifier = modifier)
            }

            is MenuState.Success -> {
                val success = response as MenuState.Success
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(items = success.menu.value, key = { UUID.randomUUID() }) {
                        MenuWeek(it, modifier = Modifier.animateItem())
                    }
                }
            }

            is MenuState.Error -> {
                val error = response as MenuState.Error
                Text("Error: {${error.error}")
            }
        }

    }
}
