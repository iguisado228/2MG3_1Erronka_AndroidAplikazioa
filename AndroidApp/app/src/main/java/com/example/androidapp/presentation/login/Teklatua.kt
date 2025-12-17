package com.example.androidapp.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun Teklatua(onKeyPress: (String) -> Unit) {
    val teklak = listOf("1","2","3","4","5","6","7","8","9","","0","←")

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in teklak.chunked(3)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEach { tekla ->
                    val isSpecial = tekla == "←" || tekla == ""
                    Button(
                        onClick = { onKeyPress(tekla) },
                        modifier = Modifier.size(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSpecial) Color(0xFFEF6C00) else Color(0xFFD7CCC8),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(tekla, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}
