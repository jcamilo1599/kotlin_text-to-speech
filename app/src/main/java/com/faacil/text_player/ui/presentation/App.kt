package com.faacil.text_player.ui.presentation

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.faacil.text_player.ui.presentation.common.organisms.AppBar
import com.faacil.text_player.ui.presentation.pages.SelectLang
import com.faacil.text_player.ui.presentation.pages.StartPage
import java.util.Locale

@Composable
fun App(
    tts: TextToSpeech?,
    languages: List<Locale>,
    selectedLanguage: Locale,
    onLanguageSelected: (Locale) -> Unit,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppBar() },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
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
    }
}