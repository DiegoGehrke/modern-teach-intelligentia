package com.diego.gehrke.learn.intelligentia.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.diego.gehrke.learn.intelligentia.R
import com.diego.gehrke.learn.intelligentia.ui.fonts.AvenirNextProFontFamily
import com.diego.gehrke.learn.intelligentia.ui.navigation.NavigationRoutes

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        val (
            username,
            userProfileImg,
            userEmail,
            goToConfigsButton,
            myActivity
        ) = createRefs()

        IconButton(onClick = { navHostController.navigate(NavigationRoutes.Settings.route) },
            modifier = Modifier
                .constrainAs(goToConfigsButton) {
                    end.linkTo(parent.end, 24.dp)
                    top.linkTo(parent.top, 24.dp)
                    width = Dimension.value(48.dp)
                    height = Dimension.value(48.dp)
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.size(28.dp)
            )
        }

        Text(
            text = "Diego Gehrke",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(username) {
                    start.linkTo(userProfileImg.end, 16.dp)
                    top.linkTo(userProfileImg.top, 8.dp)
                }
        )

        Text(
            text = "die...gog@gmail.com",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier
                .constrainAs(userEmail) {
                    start.linkTo(userProfileImg.end, 16.dp)
                    top.linkTo(username.bottom, 8.dp)
                }
        )
        
        Image(
            painter = painterResource(id = R.drawable.diego),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(userProfileImg) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(parent.top, 48.dp)
                }
                .size(88.dp)
                .clip(RoundedCornerShape(100.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Achievements",
            fontFamily = AvenirNextProFontFamily.avenirFont,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(myActivity) {
                    start.linkTo(parent.start, 24.dp)
                    top.linkTo(userProfileImg.bottom, 24.dp)
                }
        )
    }
}