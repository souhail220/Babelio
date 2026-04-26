package com.mim.babelio.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import com.mim.babelio.data.CounterViewModel
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.lerp

@Composable
fun CounterScreen(vm: CounterViewModel = viewModel()) {

    val count = vm.count.observeAsState(initial = 0)

    val lightColor = Color(0xFFFFB8B8)  // light red
    val darkColor  = Color(0xFF5D0000)  // dark red

    // Clamp count between 0–20, then normalize to 0.0–1.0
    val maxCount = 20
    val fraction = (count.value.coerceIn(0, maxCount) / maxCount.toFloat())

    // lerp smoothly blends between the two colors
    val bgColor by animateColorAsState(
        targetValue = lerp(lightColor, darkColor, fraction),
        animationSpec = tween(durationMillis = 300),
        label = "bgColor"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Count : ${count.value}",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {vm.decrement()}) { Text("-") }

            Button(onClick = {vm.increment()}) { Text("+") }
        }
    }
}