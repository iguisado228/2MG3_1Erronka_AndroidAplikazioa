package com.example.loginapp.model

data class LoginEgoera(
    val langileKodea: String = "",
    val pasahitza: String = "",
    val activeField: ActiveField = ActiveField.code
)

enum class ActiveField {
    code, Pasahitza
}
