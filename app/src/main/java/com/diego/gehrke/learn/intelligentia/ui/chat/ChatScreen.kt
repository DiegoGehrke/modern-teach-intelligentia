package com.diego.gehrke.learn.intelligentia.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.diego.gehrke.learn.intelligentia.viewmodel.ChatGptViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chatGptViewModel: ChatGptViewModel) {

    var userMessage by rememberSaveable {
        mutableStateOf("")
    }
    var chatGptMessage by rememberSaveable {
        mutableStateOf("")
    }

    val observeResponse: String? by chatGptViewModel.apiResponse.collectAsState()
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 56.dp)) {
        val constraints = portraitConstraints()
        ConstraintLayout(
            constraints,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Row(
                modifier = Modifier
                    .layoutId("supportBackground")
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth(1f)
            ) {

                TextField(
                    value = userMessage,
                    onValueChange = {
                        userMessage = it
                    },
                    label = { Text("Digite sua mensagem") },
                    placeholder = { Text("Digite aqui") },
                    modifier = Modifier
                        .layoutId("userInputField")
                        .fillMaxWidth()
                        .height(48.dp)
                )

                Button(
                    onClick = {
                        if (userMessage.isNotEmpty()) {
                            chatGptViewModel.makeRequestToChatGpt(userMessage)
                        }
                    },
                    modifier = Modifier
                        .layoutId("sendUserMessageBtn")
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Send")
                }
            }

            Text(
                text = observeResponse?: "Olá, como posso ajudar?",
                modifier = Modifier
                    .layoutId("chatGptResponseTxt")
            )


        }
    }
}

@Composable
private fun portraitConstraints(): ConstraintSet {
    return ConstraintSet {
        val editTextField = createRefFor("userInputField")
        val sendUserInputButton = createRefFor("sendUserMessageBtn")
        val chatGptResponseTxt = createRefFor("chatGptResponseTxt")
        val supportBackground = createRefFor("supportBackground")

        constrain(chatGptResponseTxt) {
            top.linkTo(parent.top, 24.dp)
            start.linkTo(parent.start, 24.dp)
            end.linkTo(parent.end, 24.dp)
            width = Dimension.fillToConstraints
        }
        constrain(sendUserInputButton) {
            end.linkTo(supportBackground.end, 8.dp)
            bottom.linkTo(supportBackground.bottom, 8.dp)
            top.linkTo(supportBackground.top)
            width = Dimension.fillToConstraints
            height = Dimension.value(52.dp)
        }

        constrain(editTextField) {
            bottom.linkTo(supportBackground.bottom, 8.dp)
            start.linkTo(supportBackground.start, 8.dp)
            top.linkTo(supportBackground.top)
            end.linkTo(sendUserInputButton.start, 4.dp)
            width = Dimension.fillToConstraints
            height = Dimension.value(56.dp)
        }

        constrain(supportBackground) {
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            height = Dimension.wrapContent
        }
    }
}


