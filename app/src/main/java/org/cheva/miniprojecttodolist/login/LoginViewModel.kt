package org.cheva.miniprojecttodolist.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnNameChanged -> changeName(event.name)
            is LoginEvent.OnPasswordChanged -> changePassword(event.password)
            is LoginEvent.OnPasswordVisibilityChanged -> changePasswordVisibility(event.isVisible)
            LoginEvent.OnLoginClicked -> login()
            LoginEvent.OnDismissDialog -> dismissDialog()
        }
    }

    private fun dismissDialog() {
        _state.update { it.copy(message = "", successLogin = false) }
    }

    private fun changeName(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }

    private fun changePassword(password: String) {
        _state.update {
            it.copy(password = password)
        }
    }

    private fun changePasswordVisibility(isVisible: Boolean) {
        _state.update {
            it.copy(passwordVisible = isVisible)
        }
    }

    private fun login() {
        if (state.value.name.isEmpty()){
            _state.update {
                it.copy(message = "Name is required")
            }
            return
        }

        if (state.value.password.isEmpty()){
            _state.update {
                it.copy(message = "Password is required")
            }
            return
        }

        _state.update {
            it.copy(message = "Login Successful", successLogin = true)
        }
    }
}