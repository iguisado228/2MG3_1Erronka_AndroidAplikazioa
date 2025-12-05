package com.example.loginapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.viewmodel.LoginPantailaViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun LoginPantaila(viewModel: LoginPantailaViewModel = viewModel()) {
    val state = viewModel.state

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF3E0) // fondo crema claro
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ONGI ETORRI",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 32.sp,
                        color = Color(0xFF6D4C41) // marrón cálido
                    )
                )

                OutlinedTextField(
                    value = state.langileKodea,
                    onValueChange = {},
                    label = { Text("LANGILE-KODEA") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6D4C41),
                        unfocusedBorderColor = Color(0xFFBCAAA4)
                    )
                )

                OutlinedTextField(
                    value = state.pasahitza,
                    onValueChange = {},
                    label = { Text("PASAHITZA") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6D4C41),
                        unfocusedBorderColor = Color(0xFFBCAAA4)
                    )
                )

                Teklatua(onKeyPress = { key -> viewModel.onKeyPressed(key) })

                Button(
                    onClick = { viewModel.onLogin() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8BC34A), // verde suave
                        contentColor = Color.White
                    )
                ) {
                    Text("SAIOA HASI", fontSize = 18.sp)
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    text = "TEKNOBIDE",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color(0xFF6D4C41)
                    ),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

