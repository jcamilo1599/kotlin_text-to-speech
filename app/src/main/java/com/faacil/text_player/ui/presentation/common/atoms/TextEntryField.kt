package com.faacil.text_player.ui.presentation.common.atoms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.faacil.text_player.R

@Composable
fun TextEntryField(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = stringResource(id = R.string.enter_text_here)) },
        modifier = Modifier.fillMaxWidth()
    )
}