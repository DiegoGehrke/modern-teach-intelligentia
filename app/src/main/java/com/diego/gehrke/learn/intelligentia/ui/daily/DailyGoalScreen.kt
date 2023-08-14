package com.diego.gehrke.learn.intelligentia.ui.daily

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FormatAlignLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import com.diego.gehrke.learn.intelligentia.ui.home.ProgressWithText

@Composable
fun DailyGoalScreen() {
    Box() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                imageVector = Icons.Rounded.FormatAlignLeft,
                contentDescription = null
            )
            Text(
                text = "Teste"
            )
        }
    }
    Column(modifier = Modifier.padding(24.dp)) {
        ProgressWithText(
            progress = 10f,
            text = "10/100"
        )
    }
}

@Composable
private fun portraitConstraints(): ConstraintSet {
    return ConstraintSet {

    }
}