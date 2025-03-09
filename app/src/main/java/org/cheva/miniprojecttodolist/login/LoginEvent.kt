package org.cheva.miniprojecttodolist.login

sealed interface LoginEvent {
    data class OnNameChanged(val name: String): LoginEvent
    data class OnPasswordChanged(val password: String): LoginEvent
    data class OnPasswordVisibilityChanged(val isVisible: Boolean): LoginEvent
    object OnDismissDialog: LoginEvent
    object OnLoginClicked: LoginEvent
}