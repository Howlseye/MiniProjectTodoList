package org.cheva.miniprojecttodolist.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.components.*
import org.cheva.miniprojecttodolist.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.navigation.DataEvent
import org.cheva.miniprojecttodolist.navigation.DataState
import org.cheva.miniprojecttodolist.navigation.LoginScreen
import org.cheva.miniprojecttodolist.ui.components.ResultDialog
import org.cheva.miniprojecttodolist.ui.theme.MiniProjectTodoListTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onNavigate: (Any) -> Unit,
    dataState: DataState,
    dataEvent: (DataEvent) -> Unit,
) {
    LaunchedEffect(state.successRegister) {
        if (state.successRegister){
            delay(1000)
            dataEvent(DataEvent.ChangeUsername(state.name))
            onNavigate(DashboardScreen)
        }
    }
    Scaffold {
        if (state.message.isNotBlank()) {
            ResultDialog(
                isSuccess = state.successRegister,
                message = state.message,
                onDismiss = { onEvent(RegisterEvent.OnDismissDialog) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            CustomText(
                text = stringResource(R.string.register_headline),
                textAlign = TextAlign.Center,
                textSize = 32.sp,
                textWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextBox(
                name = stringResource(R.string.name_label),
                placeholder = stringResource(R.string.name_hint),
                value = state.name,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "Person",
                        modifier = Modifier.size(28.dp),
                    )
                },
                onValueChange = { onEvent(RegisterEvent.OnNameChanged(it)) },
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextBox(
                name = stringResource(R.string.email_label),
                placeholder = stringResource(R.string.email_hint),
                value = state.email,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.email),
                        contentDescription = "Email",
                        modifier = Modifier.size(28.dp),
                    )
                },
                onValueChange = { onEvent(RegisterEvent.OnEmailChanged(it)) },
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextBox(
                name = stringResource(R.string.password_label),
                placeholder = stringResource(R.string.password_hint),
                value = state.password,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.lock),
                        contentDescription = "Lock",
                        modifier = Modifier.size(28.dp),
                    )
               },
                icon2 = {
                    IconButton(onClick = { onEvent(RegisterEvent.OnPasswordVisibilityChanged(!state.passwordVisible))}) {
                        Icon(
                            painter = painterResource(id = if (state.passwordVisible) R.drawable.eye2 else R.drawable.eye),
                            contentDescription = if (state.passwordVisible) "Show" else "Hide",
                            modifier = Modifier.size(28.dp),
                        )
                    }
                },
                onValueChange = { onEvent(RegisterEvent.OnPasswordChanged(it)) },
                pass = state.passwordVisible,
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(RegisterEvent.OnRegisterClicked) }
            ) {
                Text(stringResource(R.string.register_headline))
            }
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate(LoginScreen) }
            ) {
                Text(stringResource(R.string.to_login))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun RegisterScreenPrev() {
    MiniProjectTodoListTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {},
            onNavigate = {},
            dataState = DataState(),
            dataEvent = {}
        )
    }
}