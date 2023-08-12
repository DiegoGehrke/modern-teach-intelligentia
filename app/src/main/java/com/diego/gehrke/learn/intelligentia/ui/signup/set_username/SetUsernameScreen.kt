package com.diego.gehrke.learn.intelligentia.ui.signup.set_username

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.diego.gehrke.learn.intelligentia.R
import com.diego.gehrke.learn.intelligentia.domain.model.Result
import com.diego.gehrke.learn.intelligentia.ui.fonts.AvenirNextProFontFamily
import com.diego.gehrke.learn.intelligentia.ui.navigation.NavigationRoutes
import com.diego.gehrke.learn.intelligentia.ui.theme.AppTheme
import com.diego.gehrke.learn.intelligentia.viewmodel.SignupWithEmailViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SetUsernameScreen(
    navHostController: NavHostController,
    signupWithEmailViewModel: SignupWithEmailViewModel,
) {

    val context = LocalContext.current

    var username by rememberSaveable { mutableStateOf("") }
    val state: State<Boolean> = signupWithEmailViewModel.isValidName.collectAsState()
    val isButtonEnabled = state.value

    val keyboardController = LocalSoftwareKeyboardController.current

    val result: State<Result<Unit>?> = signupWithEmailViewModel.createUserResult.collectAsState()

    val loadingState by signupWithEmailViewModel.loadingState.collectAsState()

    LaunchedEffect(username) {
        signupWithEmailViewModel.onNameChanged(username)
        when (result.value) {
            is Result.Success -> {
                Toast.makeText(context, "sucesso", Toast.LENGTH_SHORT).show()
            }

            is Result.Error -> {
                Log.e("error", (signupWithEmailViewModel.createUserResult.value.toString()))
            }

            is Result.Loading -> {
            }


            else -> {}
        }
    }
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(
            MaterialTheme.colorScheme.background
        )
        .clickable(
            interactionSource = remember { MutableInteractionSource() }, indication = null
        ) {
            keyboardController?.hide()
        }) {
        val (
            backButton,
            headerText,
            nameText,
            usernameEditText,
            atLeastSomeCharacter,
            createAccountButton,
            box,
        ) = createRefs()

        IconButton(onClick = { navHostController.popBackStack() }, content = {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "back",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }, modifier = Modifier.constrainAs(backButton) {
            start.linkTo(parent.start, 24.dp)
            top.linkTo(parent.top, 24.dp)
        })

        Text(text = "Criar Conta",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(headerText) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 34.dp)
            })

        Text(text = "Qual é o seu nome?",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.constrainAs(nameText) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(backButton.bottom, 24.dp)
                bottom.linkTo(usernameEditText.top)
            })

        TextField(value = username,
            onValueChange = { username = it },
            modifier = Modifier.constrainAs(usernameEditText) {
                start.linkTo(parent.start, 24.dp)
                end.linkTo(parent.end, 24.dp)
                top.linkTo(nameText.bottom)
                width = Dimension.fillToConstraints
            },
            keyboardOptions = KeyboardOptions(
                autoCorrect = true, keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                if (isButtonEnabled) {
                    navHostController.navigate(NavigationRoutes.SetEmail.route)
                }
            }),
            singleLine = true
        )

        Text(text = "O nome deve ter pelo menos 3 letras.",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.constrainAs(atLeastSomeCharacter) {
                start.linkTo(parent.start, 24.dp)
                top.linkTo(usernameEditText.bottom, 8.dp)
            })

        Button(
            onClick = { signupWithEmailViewModel.createUserWithEmailAndPassword() },
            enabled = isButtonEnabled,
            modifier = Modifier.constrainAs(createAccountButton) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(atLeastSomeCharacter.bottom, 32.dp)
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