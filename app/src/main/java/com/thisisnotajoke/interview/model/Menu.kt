package com.thisisnotajoke.interview.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class MenuState {
    object Loading: MenuState()
    data class Success(val menu: Menu): MenuState()
    data class Error(val error: String): MenuState()
}

@Serializable
data class Menu(@SerialName("menu_week") val value: List<MenuWeek> = emptyList()) {
    companion object {
        fun fromRaw(raw: List<List<String>>): Menu {
            return Menu(
                value = raw.map { week ->
                    MenuWeek(week)
                }
            )
        }
    }
}

@Serializable
data class MenuWeek(
   val week: List<String> = emptyList(),
)