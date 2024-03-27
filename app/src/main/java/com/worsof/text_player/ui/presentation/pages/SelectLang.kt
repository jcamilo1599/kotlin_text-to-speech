package com.worsof.text_player.ui.presentation.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.worsof.text_player.ui.presentation.common.TextEntryField
import java.util.Locale

@Composable
fun SelectLang(
    languages: List<Locale>,
    selectedLanguage: Locale,
    onLanguageSelected: (Locale) -> Unit,
    navController: NavController,
) {
    LazyColumn {
        items(languages.size) { index ->
            Text(text = languages[index].displayName)
        }
    }
}