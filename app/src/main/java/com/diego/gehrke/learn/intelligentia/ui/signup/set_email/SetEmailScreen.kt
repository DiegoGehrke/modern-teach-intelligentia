package com.diego.gehrke.learn.intelligentia.ui.signup.set_email

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.diego.gehrke.learn.intelligentia.R
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.diego.gehrke.learn.intelligentia.ui.navigation.NavigationRoutes
import com.diego.gehrke.learn.intelligentia.ui.fonts.AvenirNextProFontFamily
import com.diego.gehrke.learn.intelligentia.viewmodel.SignupWithEmailViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SetEmailScreen(
    navHostController: NavHostController,
    signupWithEmailViewModel: SignupWithEmailViewModel
) {

    var email by rememberSaveable { mutableStateOf("") }
    val isValidEmail by signupWithEmailViewModel.isValidEmail.collectAsState()
    val isButtonEnabled = isValidEmail
    LaunchedEffect(email) {
        signupWithEmailViewModel.onEmailChanged(email)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme
                    .colorScheme
                    .background
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                keyboardController?.hide()
            }
    ) {
        val (
            backButton,
            headerText,
            emailText,
            emailEditText,
            needToConfirmLaterTxt,
            continueButton
        ) = createRefs()

        IconButton(
            onClick = { navHostController.popBackStack() },
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            modifier = Modifier.constrainAs(backButton) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(parent.top, 24.dp)
            }
        )

        Text(
            text = "Criar Conta",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(headerText) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, 34.dp)
                }
        )

        Text(
            text = "Qual é o seu email?",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(emailText) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(backButton.bottom, 24.dp)
                    bottom.linkTo(emailEditText.top)
                }
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .constrainAs(emailEditText) {
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(emailText.bottom)
                    width = Dimension.fillToConstraints
                },
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                if (isButtonEnabled) {
                    navHostController.navigate(NavigationRoutes.SetPassword.route)
                }
            }),
            singleLine = true
        )

        Text(
            text = "Você terá que confirmar esse email.",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(needToConfirmLaterTxt) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(emailEditText.bottom, 8.dp)
                }
        )

        Button(
            onClick = { navHostController.navigate(NavigationRoutes.SetPassword.route) },
            enabled = isButtonEnabled,
            modifier = Modifier
                .constrainAs(continueButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(needToConfirmLaterTxt.bottom, 32.dp)
                },
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Continuar",
                fontFamily = AvenirNextProFontFamily.avenirFont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

    }
}