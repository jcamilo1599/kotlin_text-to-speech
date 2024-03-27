package com.worsof.text_player.ui.presentation

import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.worsof.text_player.ui.presentation.pages.SelectLang
import com.worsof.text_player.ui.presentation.pages.StartPage
import java.util.Locale

@Composable
fun App(
    tts: TextToSpeech?,
    languages: List<Locale>,
    selectedLanguage: Locale,
    onLanguageSelected: (Locale) -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start") {
        composable(route = "start") { StartPage(tts, selectedLanguage, navController) }

        composable(route = "selectLang") {
            SelectLang(
                languages,
                selectedLanguage,
                onLanguageSelected,
                navController,
            )
        }
    }
}