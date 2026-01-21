package com.example.androidapp.presentation.txata

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RoomService
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androidapp.presentation.components.AppTopBar

import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

data class Message(
    val id: Int,
    val text: String,
    val isMe: Boolean,
    val senderIcon: ImageVector
)

@Composable
fun TxataPantaila(
    navController: NavController,
    chatId: Int,
    chatName: String
) {
    // Determine icon based on name (simple logic for prototype)
    val headerIcon = when {
        chatName.contains("Sukaldari", ignoreCase = true) -> Icons.Default.Restaurant
        chatName.contains("Zerbitzari", ignoreCase = true) -> Icons.Default.RoomService
        else -> Icons.Default.Restaurant // Default
    }

    val messages = listOf(
        Message(
            1,
            "Sukaldaria izanez gero mezua bidaltzerakoan sukaldariaren ikonoa eta izena aterako dira",
            false,
            Icons.Default.Restaurant
        ),
        Message(
            2,
            "Langile bakoitza haren postuaren ikono konkretua izango dute txatean eta temak beti berdinak izango dira, horrela haien artean hitz egin ahal izango dute distrakzio gehiegirik gabe",
            true,
            Icons.Default.RoomService
        )
    )

    var messageText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = chatName,
                navController = navController,
                titleIcon = headerIcon
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFFFF3E0)) // Light beige
        ) {
            // Messages List
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(messages) { message ->
                    MessageItem(message)
                }
            }

            // Input Area
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
            ) {
                // Text Input Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Input Box
                    OutlinedTextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        placeholder = {
                            Text(
                                text = "Soilik idatzizko mezuak bidali daitezke, ez argazki ez ezer langileak distraitu ez daitezen",
                                color = Color.Black.copy(alpha = 0.6f),
                                fontSize = 12.sp,
                                maxLines = 2,
                                lineHeight = 14.sp
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFE65100).copy(alpha = 0.1f),
                            unfocusedContainerColor = Color(0xFFE65100).copy(alpha = 0.1f),
                            focusedBorderColor = Color(0xFF42A5F5),
                            unfocusedBorderColor = Color(0xFF42A5F5),
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Send Button
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0xFFF57C00), CircleShape)
                            .clickable {
                                // Send action placeholder
                                messageText = ""
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Bidali",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                
                // Keyboard placeholder removed
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!message.isMe) {
            Icon(
                imageVector = message.senderIcon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .background(Color(0xFFFFCCBC), RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFFF57C00), RoundedCornerShape(16.dp)) // Optional border to match style
                .padding(12.dp)
        ) {
            Text(
                text = message.text,
                color = Color.Black,
                fontSize = 14.sp
            )
        }

        if (message.isMe) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = message.senderIcon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }
    }
}
