package com.thisisnotajoke.interview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.thisisnotajoke.interview.repository.LunchMenuDataSource
import com.thisisnotajoke.interview.ui.theme.InterviewTheme
import com.thisisnotajoke.interview.view.MenuList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var service: LunchMenuDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InterviewTheme {
                Box(Modifier.safeDrawingPadding()) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MenuList(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}