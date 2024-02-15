package org.unizd.rma.subasic.spacecraft

import android.os.Bundle


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.unizd.rma.peric.bookcase.presentation.contact.create.CreateSpaceCraftViewModel
import org.unizd.rma.peric.bookcase.ui.theme.*
import org.unizd.rma.subasic.spacecraft.presentation.contact.create.CreateSpaceCraftScreen
import org.unizd.rma.subasic.spacecraft.presentation.contact.details.DetailsSpaceCraftScreen
import org.unizd.rma.subasic.spacecraft.presentation.contact.details.DetailsSpaceCraftViewModel
import org.unizd.rma.subasic.spacecraft.presentation.contact.edit.UpdateSpaceCraftScreen
import org.unizd.rma.subasic.spacecraft.presentation.contact.edit.UpdateSpaceCraftViewModel
import org.unizd.rma.subasic.spacecraft.presentation.contact.list.ListSpaceCraftScreen
import org.unizd.rma.subasic.spacecraft.presentation.contact.list.ListSpaceCraftViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpaceCraftTheme {
                val navController = rememberNavController()
                Router(navController = navController)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpaceCraftTheme {
        Greeting("Android")
    }
}

@Composable
fun Router(navController: NavHostController) {
    val listSpaceCraftsViewModel: ListSpaceCraftViewModel = hiltViewModel()
    val detailsSpaceCraftViewModel: DetailsSpaceCraftViewModel = hiltViewModel()
    val createSpaceCraftViewModel: CreateSpaceCraftViewModel = hiltViewModel()
    val updateSpaceCraftViewModel: UpdateSpaceCraftViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            ListSpaceCraftScreen(navController = navController, listSpaceCraftViewModel = listSpaceCraftsViewModel)
        }

        composable("create") {

            CreateSpaceCraftScreen(navController = navController, createSpaceCraftViewModel)
        }

        // Update these on new solution
        composable("details/{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            //val expense = listExpenseViewModel.getExpenseById(itemId!!)
            if (itemId != null) {
                DetailsSpaceCraftScreen(navController = navController, detailsSpaceCraftViewModel = detailsSpaceCraftViewModel, itemId)
            }
        }

        composable("update/{id}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("id")
            if (itemId != null) {
                UpdateSpaceCraftScreen(navController = navController, updateSpaceCraftViewModel, itemId)
            }

        }

    }
}

