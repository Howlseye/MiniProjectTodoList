package org.cheva.miniprojecttodolist.login

data class LoginState (
    val name: String = "",
    val password: String = "",
    val passwordVisible: Boolean = true,
    val message: String = "",
    val successLogin: Boolean = false
)