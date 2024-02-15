package org.unizd.rma.subasic.spacecraft.presentation.contact.edit

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.runBlocking
import org.unizd.rma.subasic.spacecraft.presentation.components.showDatePickerUpdate
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.unizd.rma.subasic.spacecraft.R
import org.unizd.rma.subasic.spacecraft.presentation.components.SpaceCraftInput

@Composable
fun UpdateSpaceCraftScreen(
    navController: NavController,
    updateSpaceCraftViewModel: UpdateSpaceCraftViewModel = hiltViewModel(),
    id: String
) {
    // Dohvat trenutnih podataka svemirskog broda pri pokretanju ekrana

    val context = LocalContext.current

    val spacecraft = LocalContext.current
    val file = spacecraft.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(spacecraft),
        spacecraft.packageName + ".provider",
        file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()) {
        Log.d("UpdateSpaceCraftScreen", "CameraLauncher $it")
        capturedImageUri = uri
        updateSpaceCraftViewModel.onImageChange(capturedImageUri.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()) {

        if(it) {
            Toast.makeText(spacecraft, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(spacecraft, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = "Uredi svemirski brod")
                },
                actions = {
                    Button(onClick = {
                        runBlocking {
                            updateSpaceCraftViewModel.updateSpaceCraft(id)
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
            Text(text = "Ime svemirskog broda")
            Spacer(modifier = Modifier.width(16.dp))
            SpaceCraftInput(
                value = updateSpaceCraftViewModel.name,
                onChange = { value ->
                    updateSpaceCraftViewModel.onNameChange(value)
                },
                placeholder = "Unesite ime svemirskog broda")


            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Model")
            Spacer(modifier = Modifier.width(16.dp))

            SpaceCraftInput(
                value = updateSpaceCraftViewModel.model,
                onChange = { value ->
                    updateSpaceCraftViewModel.onModelChange(value)
                },
                placeholder = "Unesite model svemirskog broda"

            )

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Brzina")
            Spacer(modifier = Modifier.width(16.dp))

            SpaceCraftInput(
                value = updateSpaceCraftViewModel.speed.toString(),
                onChange = { value ->
                    updateSpaceCraftViewModel.onSpeedChange(value.toDouble())
                },
                placeholder = "Unesite brzinu svemirskog broda"

            )

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Težina")
            Spacer(modifier = Modifier.width(16.dp))

            SpaceCraftInput(
                value = updateSpaceCraftViewModel.weight.toString(),
                onChange = { value ->
                    updateSpaceCraftViewModel.onWeightChange(value.toDouble())
                },
                placeholder = "Unesite težinu svemirskog broda"

            )

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "Datum")

            showDatePickerUpdate(context, updateSpaceCraftViewModel = updateSpaceCraftViewModel)

            Spacer(modifier = Modifier.width(16.dp))
            Spacer(modifier = Modifier.height(25.dp))

            Button(onClick = {
                val permissionCheckResult = ContextCompat.checkSelfPermission(spacecraft,
                    Manifest.permission.CAMERA)

                if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Text(text = "Dodaj fotografiju.")

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
