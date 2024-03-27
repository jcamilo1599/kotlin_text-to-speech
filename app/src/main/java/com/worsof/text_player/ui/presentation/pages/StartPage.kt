package com.worsof.text_player.ui.presentation.pages

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.worsof.text_player.ui.presentation.common.NavSelectLangBtn
import com.worsof.text_player.ui.presentation.common.PlayTextBtn
import com.worsof.text_player.ui.presentation.common.TextEntryField
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartPage(tts: TextToSpeech?, selectedLanguage: Locale, navController: NavController) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Reproductor de Texto")
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
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
                    NavSelectLangBtn(selectedLanguage, navController)
                    Spacer(modifier = Modifier.height(16.dp))
                    TextEntryField(text = text, onTextChange = { text = it })
                    Spacer(modifier = Modifier.height(16.dp))
                    PlayTextBtn(text, tts)
                }
            }
        }
    }
}