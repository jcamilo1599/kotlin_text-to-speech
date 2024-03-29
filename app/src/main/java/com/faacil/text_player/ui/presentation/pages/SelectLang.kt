package com.faacil.text_player.ui.presentation.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.faacil.text_player.R
import java.util.Locale

@Composable
fun SelectLang(
    languages: List<Locale>,
    selectedLanguage: Locale,
    onLanguageSelected: (Locale) -> Unit,
    navController: NavController,
) {
    // Estado para almacenar el texto del filtro
    val filterText = remember { mutableStateOf("") }

    // Filtra la lista de idiomas basándote en el texto del filtro
    val filteredLanguages = languages.filter {
        it.displayName.contains(filterText.value, ignoreCase = true)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Campo de texto para la entrada del filtro
            OutlinedTextField(
                value = filterText.value,
                onValueChange = { filterText.value = it },
                label = { Text(text = stringResource(id = R.string.filter_lang)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            // Usar LazyColumn para mostrar los idiomas filtrados
            LazyColumn {
                items(filteredLanguages.size) { index ->
                    ListItem(
                        modifier = Modifier
                            .clickable {
                                onLanguageSelected(filteredLanguages[index])
                                navController.popBackStack()
                            },
                        headlineContent = { Text(filteredLanguages[index].displayName) },
                        supportingContent = { Text(filteredLanguages[index].isO3Language) },
                        trailingContent = {
                            if (selectedLanguage == filteredLanguages[index]) {
                                Icon(
                                    Icons.Filled.Done,
                                    contentDescription = "",
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

