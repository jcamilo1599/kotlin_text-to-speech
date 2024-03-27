package com.worsof.text_player

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.worsof.text_player.ui.presentation.App
import java.util.*

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private lateinit var ttsReady: MutableState<Boolean>

    // Idioma selecciona.
    // Inicialmente tiene el idioma predeterminado del dispositivo.
    private var selectedLanguage by mutableStateOf(Locale.getDefault())

    // Lista de idiomas disponibles
    private var languages = mutableListOf<Locale>()
    private var seenLanguages = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ttsReady = mutableStateOf(false)
        tts = TextToSpeech(this, this)

        // Mapea los idiomas disponibles
        for (locale in Locale.getAvailableLocales()) {
            if (locale.language !in seenLanguages) {
                languages.add(locale)
                seenLanguages.add(locale.language)
            }
        }

        setContent {
            App(tts, languages, selectedLanguage) { newLocale ->
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
