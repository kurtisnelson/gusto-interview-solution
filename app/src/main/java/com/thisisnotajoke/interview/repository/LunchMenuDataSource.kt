package com.thisisnotajoke.interview.repository

import kotlinx.coroutines.delay
import javax.inject.Singleton

@Singleton
class LunchMenuDataSource {

    suspend fun getLunchMenu(): List<List<String>> {
        delay(3_000)
        return listOf(
            listOf("Chicken and waffles", "Tacos", "Curry", "Pizza", "Sushi"),
            listOf("Breakfast for lunch", "Hamburgers", "Spaghetti", "Salmon", "Sandwiches")
        )
    }
}