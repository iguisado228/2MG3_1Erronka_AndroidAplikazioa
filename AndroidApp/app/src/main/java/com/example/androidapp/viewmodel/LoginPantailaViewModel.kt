package com.example.loginapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.loginapp.model.LoginEgoera
import com.example.loginapp.model.ActiveField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LoginPantailaViewModel : ViewModel() {
    var state by mutableStateOf(LoginEgoera())
        private set

    fun onKeyPressed(key: String) {
        when (key) {
            "←" -> {
                state = state.copy(
                    langileKodea = if (state.activeField == ActiveField.code)
                        state.langileKodea.dropLast(1)
                    else state.langileKodea,
                    pasahitza = if (state.activeField == ActiveField.Pasahitza)
                        state.pasahitza.dropLast(1)
                    else state.pasahitza
                )
            }
            "OK" -> {
                state = state.copy(
                    activeField = if (state.activeField == ActiveField.code)
                        ActiveField.Pasahitza
                    else ActiveField.code
                )
            }
            else -> {
                state = if (state.activeField == ActiveField.code) {
                    state.copy(langileKodea = state.langileKodea + key)
                } else {
                    state.copy(pasahitza = state.pasahitza + key)
                }
            }
        }
    }

    fun onLogin() {
        println("Langile Kodea: ${state.langileKodea}, Pasahitza: ${state.pasahitza}")
    }
}
