package com.worsof.text_player.ui.presentation.common

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDropdown(
    languages: List<Locale>,
    selectedLanguage: Locale,
    onLanguageSelected: (Locale) -> Unit,
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    // Texto del idioma selecciona
    val languageLabel = selectedLanguage.getDisplayLanguage(selectedLanguage)

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
                    text = { Text(text = locale.displayName) },
                    onClick = {
                        expanded = false
                        onLanguageSelected(locale)

                        Toast.makeText(
                            context,
                            "Idioma de reproducción: ${locale.displayName}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }
        }
    }
}