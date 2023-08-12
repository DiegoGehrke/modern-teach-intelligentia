package com.diego.gehrke.learn.intelligentia.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.diego.gehrke.learn.intelligentia.R
import com.diego.gehrke.learn.intelligentia.ui.fonts.AvenirNextProFontFamily
import com.diego.gehrke.learn.intelligentia.viewmodel.SettingsScreenViewModel

@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel,
    navHostController: NavHostController
) {

    val scrollState = rememberScrollState()
    /* var darkThemeCheckedState by rememberSaveable {
         mutableStateOf(false)
     }*/
    val darkThemeCheckedState by settingsScreenViewModel.isDarkTheme
    val languagesList = settingsScreenViewModel.languagesList
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (
            backButton,
            title,
            scrollView,
            darkThemeSwitch,
        ) = createRefs()
        IconButton(onClick = { navHostController.popBackStack() },
            modifier = Modifier
                .constrainAs(backButton) {
                    start.linkTo(parent.start, 4.dp)
                    top.linkTo(parent.top, 24.dp)
                    width = Dimension.value(48.dp)
                    height = Dimension.value(48.dp)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = "Settings",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(backButton.end, 4.dp)
                top.linkTo(backButton.top)
                bottom.linkTo(backButton.bottom)
            }
        )

        Column(modifier = Modifier
            .fillMaxHeight()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
            .constrainAs(scrollView) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(backButton.bottom, 24.dp)
            }
        ) {
            SettingSwitchItem(
                checked = darkThemeCheckedState,
                onCheckedChange = {
                    settingsScreenViewModel.changeTheme()
                },
                title = "Dark mode",
                description = "Recommended for places with low light"
            )

            DropDownMenu(languagesList)
        }

    }
}

@Composable
private fun SettingSwitchItem(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    title: String,
    description: String,
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .toggleable(
                value = checked,
                enabled = enabled,
                role = Role.Switch,
                onValueChange = onCheckedChange
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1.0f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val contentAlpha = if (enabled) 1.0F else 0.0F

            Text(
                text = title,
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                maxLines = 1,
                modifier = Modifier.alpha(contentAlpha)
            )
            Text(
                text = description,
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.alpha(contentAlpha)
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = null,
            enabled = enabled,
            modifier = Modifier.scale(scaleX = 0.9F, scaleY = 0.85F),
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.onTertiary,
                checkedTrackColor = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    languagesList: List<String>
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedText by rememberSaveable { mutableStateOf(languagesList[0]) }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(end = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "Application language",
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
        Box(
            modifier = Modifier
                .height(124.dp)
                .width(184.dp)
                .padding(32.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    languagesList.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

