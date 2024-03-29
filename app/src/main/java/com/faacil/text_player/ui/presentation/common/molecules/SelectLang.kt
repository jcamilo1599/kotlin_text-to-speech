package com.faacil.text_player.ui.presentation.common.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.faacil.text_player.R
import com.faacil.text_player.ui.presentation.common.atoms.CustomCard
import java.util.Locale

@Composable
fun SelectLangMolecule(selectedLanguage: Locale, navController: NavController) {
    val lang = stringResource(id = R.string.lang)

    CustomCard(content = {
        Column(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 12.dp,
            ),
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "$lang: ${selectedLanguage.displayName}",
                    fontWeight = FontWeight.Black,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                // Empuja el botón hacia la derecha
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { navController.navigate("selectLang") },
                    content = {
                        Text(text = stringResource(id = R.string.change))
                    }
                )
            }
        }
    })
}