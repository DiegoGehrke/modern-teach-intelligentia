package com.diego.gehrke.learn.intelligentia.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.ModeNight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.diego.gehrke.learn.intelligentia.ui.fonts.AvenirNextProFontFamily
import com.diego.gehrke.learn.intelligentia.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
) {
    val isDarkTheme: Boolean = isSystemInDarkTheme()

    BoxWithConstraints {
        val constraints = portraitConstraints()

        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Boa noite,",
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.layoutId("gentleText")
            )

            Text(
                text = "Diego Gehrke!",
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.layoutId("username")
            )

            Box(
                modifier = Modifier.layoutId("changeThemeButton"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Rounded.LightMode else Icons.Rounded.ModeNight,
                    contentDescription = if (isDarkTheme) "Switch do light mode" else "Switch to dark mode",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(32.dp)
                )
            }
            GroupedTextWithIcon(
                text1 = "English Basics",
                text2 = "Learn the alphabet, \ncumpriments and basic words.",
                modifier = Modifier.layoutId("englishBasics")
            )
        }
    }
}

@Composable
fun GroupedTextWithIcon(
    text1: String,
    text2: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(8.dp)
            ), verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Text(
                text = text1,
                fontSize = 20.sp,
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = text2,
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row {
                ProgressWithText(progress = 0.5f, text = "50/100")
            }


        }
    }
}

@Composable
fun ProgressWithText(progress: Float, text: String) {
    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .progressSemantics(value = progress, valueRange = 0.0F..1.0F),
        color = MaterialTheme.colorScheme.outline,
        trackColor = MaterialTheme.colorScheme.background
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun portraitConstraints(): ConstraintSet {
    return ConstraintSet {
        val gentleText = createRefFor("gentleText")
        val username = createRefFor("username")
        val changeThemeButton = createRefFor("changeThemeButton")
        val englishBasics = createRefFor("englishBasics")
        constrain(gentleText) {
            top.linkTo(parent.top, 24.dp)
            start.linkTo(parent.start, 24.dp)
        }
        constrain(username) {
            top.linkTo(gentleText.bottom)
            start.linkTo(gentleText.start)
        }
        constrain(changeThemeButton) {
            top.linkTo(parent.top, 24.dp)
            end.linkTo(parent.end, 24.dp)
            height = Dimension.value(48.dp)
            width = Dimension.value(48.dp)
        }
        constrain(englishBasics) {
            top.linkTo(username.bottom, 48.dp)
            start.linkTo(parent.start, 64.dp)
            end.linkTo(parent.end, 64.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(148.dp)
        }
    }
}