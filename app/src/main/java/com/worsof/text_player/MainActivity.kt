package com.worsof.text_player

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import java.util.*

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private lateinit var ttsReady: MutableState<Boolean>

    // Idioma selecciona.
    // Inicialmente tiene el idioma predeterminado del dispositivo.
    private var selectedLanguage by mutableStateOf(Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ttsReady = mutableStateOf(false)
        tts = TextToSpeech(this, this)

        setContent {
            App(tts, selectedLanguage) { newLocale ->
                selectedLanguage = newLocale
                tts?.language = selectedLanguage
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = selectedLanguage
            ttsReady.value = true
        } else {
            ttsReady.value = false
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}

@Composable
fun App(tts: TextToSpeech?, selectedLanguage: Locale, onLanguageSelected: (Locale) -> Unit) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

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
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            LanguageDropdown(selectedLanguage, onLanguageSelected)
            Spacer(modifier = Modifier.height(8.dp))
            TextEntryField(text = text, onTextChange = { text = it })
            Spacer(modifier = Modifier.height(8.dp))
            PlayTextButton(text, tts)
        }
    }
}

@Composable
fun TextEntryField(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text("Ingresa el texto aquí...") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PlayTextButton(text: String, tts: TextToSpeech?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        // Empuja el botón hacia la derecha
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "") },
        ) {
            Text("Reproducir")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(selectedLanguage: Locale, onLanguageSelected: (Locale) -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    // Texto del idioma selecciona
    val languageLabel = selectedLanguage.getDisplayLanguage(selectedLanguage)

    // Idiomas disponibles
    val languages = arrayOf(
        Locale.US,
        Locale("es"),
    )

    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = Modifier.fillMaxWidth(),
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        OutlinedTextField(
            value = languageLabel,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            label = { Text("Idioma de reproducción") },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            languages.forEach { locale ->
                DropdownMenuItem(
                    text = { Text(text = locale.getDisplayLanguage(locale)) },
                    onClick = {
                        expanded = false
                        onLanguageSelected(locale)

                        Toast.makeText(
                            context,
                            "Idioma de reproducción: ${locale.getDisplayLanguage(locale)}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }
        }
    }
}
