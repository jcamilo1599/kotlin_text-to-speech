package com.worsof.text_player.ui.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextEntryField(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text("Ingresa el texto aquí...") },
        modifier = Modifier.fillMaxWidth()
    )
}