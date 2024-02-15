package org.unizd.rma.subasic.spacecraft.presentation.contact.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.unizd.rma.subasic.spacecraft.R
import org.unizd.rma.subasic.spacecraft.domain.models.SpaceCraftResponseModel


@Composable
fun DetailsSpaceCraftScreen(
    navController: NavController,
    detailsSpaceCraftViewModel: DetailsSpaceCraftViewModel,
    id: String
) {
    LaunchedEffect(Unit) {
        detailsSpaceCraftViewModel.loadSpaceCraftDetails(id)
    }

    val spaceCraft: SpaceCraftResponseModel? = detailsSpaceCraftViewModel.spaceCraft


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Podaci o svemirskom brodu")
                },
                actions = {
                    Button(onClick = {
                        navController.popBackStack()
                    }) {
                        Text(text = "Back")
                    }
                }
            )
        }
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ){
            Text(text = "Ime svemirskog broda")
            Spacer(modifier = Modifier.width(16.dp))
            Text(spaceCraft?.ime ?: "")
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Model")
            Spacer(modifier = Modifier.width(16.dp))
            Text(spaceCraft?.model ?: "")
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Brzina")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Brzina: ${spaceCraft?.brzina ?: ""}")
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Slika")
            Spacer(modifier = Modifier.width(16.dp))
            spaceCraft?.let { spaceCraft ->
                if (spaceCraft.slika.path?.isNotEmpty() == true) {
                    Image(
                        modifier = Modifier.padding(16.dp, 16.dp),
                        painter = rememberImagePainter(spaceCraft.slika),
                        contentDescription = "Image"
                    )
                } else {
                    Image(
                        modifier = Modifier.padding(16.dp, 16.dp),
                        painter = painterResource(id = R.drawable.default_img),
                        contentDescription = "Image"
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Težina")
            Spacer(modifier = Modifier.width(16.dp))
            Text("Brzina: ${spaceCraft?.tezina ?: ""}")
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Datum")
            Spacer(modifier = Modifier.width(16.dp))
            Text(spaceCraft?.datum ?: "")
            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}
