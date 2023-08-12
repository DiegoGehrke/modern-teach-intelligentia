package com.diego.gehrke.learn.intelligentia.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.diego.gehrke.learn.intelligentia.R
import com.diego.gehrke.learn.intelligentia.ui.navigation.NavigationRoutes
import com.diego.gehrke.learn.intelligentia.ui.fonts.NeuzeitOfficeFontFamily

@Composable
fun WelcomeScreen(navHostController: NavHostController) {

    val neuzeitFontFamily = NeuzeitOfficeFontFamily

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (
            goToCreateAccountButton,
            goToLoginButton,
            logoImg,
            createAccountWithGoogleButton,
            continueAsGuest
        ) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(logoImg) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(goToCreateAccountButton.top, 48.dp)
                }
        )

        Button(onClick = { navHostController.navigate(NavigationRoutes.SetEmail.route) },
            modifier = Modifier
                .constrainAs(goToCreateAccountButton) {
                    start.linkTo(parent.start, 48.dp)
                    end.linkTo(parent.end, 48.dp)
                    bottom.linkTo(goToLoginButton.top, 24.dp)
                    height = Dimension.value(48.dp)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = "Criar conta",
                fontFamily = neuzeitFontFamily.neuzeiFont,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.background,
                fontSize = 20.sp
            )
        }

        Button(onClick = { navHostController.navigate(NavigationRoutes.SetEmail.route) },
            modifier = Modifier
                .constrainAs(goToLoginButton) {
                    start.linkTo(parent.start, 48.dp)
                    end.linkTo(parent.end, 48.dp)
                    bottom.linkTo(continueAsGuest.top, 24.dp)
                    height = Dimension.value(48.dp)
                    width = Dimension.fillToConstraints
                },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = "Já possuo uma conta",
                fontFamily = neuzeitFontFamily.neuzeiFont,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.background,
                fontSize = 20.sp
            )
        }

        TextButton(onClick = { navHostController.navigate(NavigationRoutes.SetEmail.route) },
            modifier = Modifier
                .constrainAs(continueAsGuest) {
                    start.linkTo(parent.start, 48.dp)
                    end.linkTo(parent.end, 48.dp)
                    bottom.linkTo(parent.bottom, 48.dp)
                    height = Dimension.value(48.dp)
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(
                text = "Continuar sem cadastro",
                fontFamily = neuzeitFontFamily.neuzeiFont,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp
            )
        }

    }
}