package com.thisisnotajoke.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thisisnotajoke.interview.model.Menu
import com.thisisnotajoke.interview.model.MenuState
import com.thisisnotajoke.interview.repository.LunchMenuDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LunchMenuViewModel @Inject constructor(
    private val service: LunchMenuDataSource
) : ViewModel() {

    init {
        refresh()
    }

    private val _menu = MutableStateFlow<MenuState>(MenuState.Loading)
    val menu: StateFlow<MenuState> = _menu

    fun refresh(onComplete: () -> Unit = {}) {
        viewModelScope.launch {
            fetchMenu()
            onComplete()
        }
    }

    private suspend fun fetchMenu() {
        try {
            val result = service.getLunchMenu()
            _menu.value = MenuState.Success(Menu.fromRaw(result))
        } catch (e: IllegalArgumentException) {
            _menu.value = MenuState.Error(error = e.message ?: "Unknown error")
        }
    }
}

