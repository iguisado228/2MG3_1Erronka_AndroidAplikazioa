package com.example.androidapp.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.androidapp.data.model.EremuEzarri
import androidx.navigation.NavController

@Composable
fun LoginPantaila(
    navController: NavController,
    viewModel: LoginPantailaViewModel = viewModel()
) {
    val egoera = viewModel.egoera
    var erroreMezua  by remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF3E0)
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
                        color = Color(0xFF6D4C41)
                    )
                )

                OutlinedTextField(
                    value = egoera.langileKodea,
                    onValueChange = {},
                    label = { Text("LANGILE-KODEA") },
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable { viewModel.eremuEzarri (EremuEzarri .Kodea) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6D4C41),
                        unfocusedBorderColor = Color(0xFFBCAAA4)
                    )
                )

                OutlinedTextField(
                    value = egoera.pasahitza,
                    onValueChange = {},
                    label = { Text("PASAHITZA") },
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clickable { viewModel.eremuEzarri (EremuEzarri .Pasahitza) },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6D4C41),
                        unfocusedBorderColor = Color(0xFFBCAAA4)
                    )
                )

                Teklatua(onKeyPress = { key -> viewModel.teklaZapaldu(key) })

                Button(
                    onClick = {
                        viewModel.loginEgin(
                            onSuccess = { navController.navigate("menu") },
                            onError = { msg -> erroreMezua  = msg }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8BC34A),
                        contentColor = Color.White
                    )
                ) {
                    if (egoera.loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(22.dp)
                        )
                    } else {
                        Text("SAIOA HASI", fontSize = 18.sp)
                    }
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

            erroreMezua ?.let { msg ->
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    action = {
                        TextButton(onClick = { erroreMezua  = null }) {
                            Text("Itxi")
                        }
                    }
                ) { Text(msg) }
            }
        }
    }
}
