package com.d_vide.D_VIDE.app.presentation.Login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d_vide.D_VIDE.app.domain.util.log
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableFieldItem
import com.d_vide.D_VIDE.app.presentation.PostRecruiting.component.EditableTextField
import com.d_vide.D_VIDE.app.presentation.component.BottomButton
import com.d_vide.D_VIDE.app.presentation.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is LoginViewModel.UiEvent.Error -> {
                    "LOGIN ERROR!!".log()
                }
                is LoginViewModel.UiEvent.Login -> {
                    "LOGIN SUCCESS!!".log()
                    navController.navigate(Screen.HomeScreen.route)
                }
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        EditableFieldItem(labelText = "ID") {
            EditableTextField(
                inputText = viewModel.emailPw.value.email,
                onValueChange = { viewModel.onEvent(LoginEvent.EnteredEmail(it)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
        }
        EditableFieldItem(labelText = "PW") {
            EditableTextField(
                inputText = viewModel.emailPw.value.password,
                onValueChange = { viewModel.onEvent(LoginEvent.EnteredPassword(it)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
        Spacer(modifier = Modifier.size(150.dp))
        BottomButton(text =  "로그인", onClick = { viewModel.onEvent(LoginEvent.Login) })
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    LoginScreen(rememberNavController())
}