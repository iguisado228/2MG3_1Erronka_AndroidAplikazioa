package com.example.androidapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androidapp.core.network.ApiClient
import com.example.androidapp.data.dto.LangileaDto
import com.example.androidapp.data.dto.LoginEskaera
import com.example.androidapp.data.model.EremuEzarri
import com.example.androidapp.data.model.LoginEgoera
import com.example.androidapp.data.remote.LoginApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPantailaViewModel : ViewModel() {
    var egoera by mutableStateOf(LoginEgoera())
        private set

    private val loginApi: LoginApi by lazy {
        ApiClient.retrofit.create(LoginApi::class.java)
    }

    fun eremuEzarri(field: EremuEzarri) {
        egoera = egoera.copy(eremuEzarri = field)
    }

    fun teklaZapaldu(key: String) {
        when (key) {
            "←" -> {
                egoera = egoera.copy(
                    langileKodea = if (egoera.eremuEzarri == EremuEzarri .Kodea)
                        egoera.langileKodea.dropLast(1) else egoera.langileKodea,
                    pasahitza = if (egoera.eremuEzarri == EremuEzarri .Pasahitza)
                        egoera.pasahitza.dropLast(1) else egoera.pasahitza
                )
            }
            else -> {
                if (key.isNotEmpty()) {
                    egoera = if (egoera.eremuEzarri == EremuEzarri .Kodea) {
                        egoera.copy(langileKodea = egoera.langileKodea + key)
                    } else {
                        egoera.copy(pasahitza = egoera.pasahitza + key)
                    }
                }
            }
        }
    }

    fun loginEgin(
        onSuccess: (LangileaDto) -> Unit,
        onError: (String) -> Unit
    ) {
        val kodea = egoera.langileKodea.toIntOrNull()
        if (kodea == null || egoera.pasahitza.isBlank()) {
            onError("Kodea zenbaki bat izan behar da eta pasahitza ezin da hutsik egon.")
            return
        }

        val request = LoginEskaera(
            Langile_kodea = kodea,
            Pasahitza = egoera.pasahitza
        )

        loginApi.login(request).enqueue(object : Callback<LangileaDto> {
            override fun onResponse(call: Call<LangileaDto>, response: Response<LangileaDto>) {
                egoera = egoera.copy(loading = false)
                if (response.isSuccessful) {
                    val langilea = response.body()
                    if (langilea != null) {
                        onSuccess(langilea)
                    } else {
                        onError("Errorea: gorputz hutsa.")
                    }
                } else {
                    onError("Login okerra (${response.code()}).")
                }
            }

            override fun onFailure(call: Call<LangileaDto>, t: Throwable) {
                egoera = egoera.copy(loading = false)
                onError("Sare errorea: ${t.message ?: "Ezezaguna"}")
            }
        })
    }
}

