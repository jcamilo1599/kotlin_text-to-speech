package com.worsof.text_player

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.worsof.text_player.ui.presentation.App
import com.worsof.text_player.ui.theme.TextPlayerTheme
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
            TextPlayerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    App(tts, languages, selectedLanguage) { newLang ->
                        selectedLanguage = newLang
                        tts?.language = selectedLanguage

                        val playbackLang = R.string.playback_lang

                        Toast.makeText(
                            this,
                            "$playbackLang: ${newLang.displayName}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
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
