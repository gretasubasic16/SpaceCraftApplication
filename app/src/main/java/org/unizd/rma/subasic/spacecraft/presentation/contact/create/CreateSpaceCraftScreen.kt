package org.unizd.rma.subasic.spacecraft.presentation.contact.create

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import kotlinx.coroutines.runBlocking
import org.unizd.rma.peric.bookcase.presentation.contact.create.CreateSpaceCraftViewModel
import org.unizd.rma.subasic.spacecraft.R
import org.unizd.rma.subasic.spacecraft.presentation.components.GenreSpinnerExample
import org.unizd.rma.subasic.spacecraft.presentation.components.SpaceCraftInput
import org.unizd.rma.subasic.spacecraft.presentation.components.showDatePicker
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects


@Composable
fun CreateSpaceCraftScreen(
    navController: NavController,
    createSpaceCraftViewModel: CreateSpaceCraftViewModel = hiltViewModel()
) {
    Log.d("CreateSpaceCraftScreen", "Create")

    val context = LocalContext.current

    val spaceCraft = LocalContext.current
    val file = spaceCraft.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(spaceCraft),
        spaceCraft.packageName + ".provider",
        file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()) {
        Log.d("CreateSpaceCraftScreen", "CameraLauncher $it")
        capturedImageUri = uri
        createSpaceCraftViewModel.promijeniSliku(capturedImageUri.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) {

        if(it) {
            Toast.makeText(spaceCraft, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(spaceCraft, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Nova letjelica")
                },
                actions = {
                    Button(onClick = {
                        runBlocking {
                            createSpaceCraftViewModel.kreirajSpaceCraft()
                            navController.popBackStack()
                        }
                    }) {
                        Text(text = "Spremi")
                    }
                }
            )
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Ime letjelice")
            Spacer(modifier = Modifier.width(16.dp))
            SpaceCraftInput(
                value = createSpaceCraftViewModel.ime,
                onChange = {
                        value ->
                    createSpaceCraftViewModel.promijeniIme(value)
                },
                placeholder = "Unesite ime")

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Brzina")
            Spacer(modifier = Modifier.width(16.dp))

            val doublePattern = remember { Regex("^\\d*\\.?\\d*\$") }
            var text by remember { mutableStateOf(createSpaceCraftViewModel.brzina) }

            SpaceCraftInput(
                value = createSpaceCraftViewModel.brzina.toString(),
                onChange = {
                        value ->
                    createSpaceCraftViewModel.promijeniBrzinu(value.toDouble())
                    //createSpacecraftViewModel.onNameChange(it)
                },
                placeholder = "Unesite brzinu."

            )


            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Izaberite model")
            Spacer(modifier = Modifier.width(16.dp))
            // category dropdown goes here
            GenreSpinnerExample(createSpaceCraftViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Datum kreiranja")
            Spacer(modifier = Modifier.width(16.dp))

            showDatePicker(context, createSpaceCraftViewModel = createSpaceCraftViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = {
                val permissionCheckResult = ContextCompat.checkSelfPermission(spaceCraft,
                    Manifest.permission.CAMERA)

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Text(text = "Dodaj sliku.")

            }

            Spacer(modifier = Modifier.width(16.dp))

            if (capturedImageUri.path?.isNotEmpty() == true) {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = rememberImagePainter(capturedImageUri),
                    contentDescription = "Image")
            } else {
                Image(
                    modifier = Modifier.padding(16.dp, 16.dp),
                    painter = painterResource(id = R.drawable.default_img),
                    contentDescription = "Image")
            }




        }

    }


}


fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(Date())
    val imageFileName = "image" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".jpg",
        externalCacheDir
    )
}
