package com.mim.babelio.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mim.babelio.ui.navigation.AppNavGraph

fun setMainScreenContent(activity: ComponentActivity) {
    activity.setContent {
        AppNavGraph();
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    AppNavGraph()
}