package com.mim.babelio.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mim.babelio.ui.screens.CounterScreen

@Composable
fun MainScreen() {
    MaterialTheme {
        Surface {
            Text("Hello World!")

        }
    }
}

fun setMainScreenContent(activity: ComponentActivity) {
    activity.setContent {
        CounterScreen();
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}