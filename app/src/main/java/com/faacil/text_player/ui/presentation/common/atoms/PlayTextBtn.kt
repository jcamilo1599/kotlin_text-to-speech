package com.faacil.text_player.ui.presentation.common.atoms

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.faacil.text_player.R

@Composable
fun PlayTextBtn(text: String, tts: TextToSpeech?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Empuja el botón hacia la derecha
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "") },
        ) {
            Text(text = stringResource(id = R.string.play))
        }
    }
}