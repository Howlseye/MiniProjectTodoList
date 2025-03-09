package org.cheva.miniprojecttodolist.login

data class LoginState (
    val name: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val message: String = "",
    val successLogin: Boolean = false,
)