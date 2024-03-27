package com.worsof.text_player.ui.presentation.pages

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.worsof.text_player.ui.presentation.common.molecules.SelectLangMolecule
import com.worsof.text_player.ui.presentation.common.atoms.PlayTextBtn
import com.worsof.text_player.ui.presentation.common.atoms.TextEntryField
import java.util.Locale

@Composable
fun StartPage(tts: TextToSpeech?, selectedLanguage: Locale, navController: NavController) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                // Detectar eventos de toque y quitar el foco del TextField
                detectTapGestures(
                    onPress = {
                        focusManager.clearFocus()
                    }
                )
            }
            .padding(
                horizontal = 14.dp,
                vertical = 20.dp,
            )
    ) {
        Column {
            SelectLangMolecule(selectedLanguage, navController)
            Spacer(modifier = Modifier.height(10.dp))
            TextEntryField(text = text, onTextChange = { text = it })
            Spacer(modifier = Modifier.height(4.dp))
            PlayTextBtn(text, tts)
        }
    }
}