package org.cheva.miniprojecttodolist.register

data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = true,
    val message: String = "",
    val successRegister: Boolean = false,
)
