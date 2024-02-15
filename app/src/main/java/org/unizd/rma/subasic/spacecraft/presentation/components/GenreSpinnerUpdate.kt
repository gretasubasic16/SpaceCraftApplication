package org.unizd.rma.subasic.spacecraft.presentation.components

import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.unizd.rma.subasic.spacecraft.presentation.contact.edit.UpdateSpaceCraftViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreSpinnerUpdate(updateSpaceCraftViewModel: UpdateSpaceCraftViewModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it }) {

        TextField(
            value = updateSpaceCraftViewModel.model,
            onValueChange = {
                // This can be left empty, as the value is managed by the ViewModel
            },
            readOnly = true,
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )

        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {

            listOf("RAKETA", "SONDA", "ORBITALNE SVEMIRSKE STANICE", "SATELITI").forEach { model ->
                DropdownMenuItem(
                    onClick = {
                        updateSpaceCraftViewModel.onModelChange(model)
                        isExpanded = false
                    }) {

                    Text(text = model)
                }
            }
        }
    }
}
