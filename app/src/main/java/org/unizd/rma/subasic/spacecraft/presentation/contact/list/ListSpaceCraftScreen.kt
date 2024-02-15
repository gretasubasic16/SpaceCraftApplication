package org.unizd.rma.subasic.spacecraft.presentation.contact.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking

@Composable
fun ListSpaceCraftScreen(
    navController: NavController,
    listSpaceCraftViewModel: ListSpaceCraftViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        listSpaceCraftViewModel.getSpaceCrafts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lista svemirskih brodova")
                },
                actions = {
                    Button(onClick = {
                        navController.navigate("create")
                    }) {
                        Text(text = "New")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            LazyColumn(modifier = Modifier.fillMaxHeight()) {
                var num = 1
                items(listSpaceCraftViewModel.spaceCrafts) { item ->
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .background(if (num % 2 == 0) Color.LightGray else Color.Gray)
                            .clickable { /* Handle row click if needed */ }
                    ) {
                        Column {
                            Text(text = "$num. ${item.name}")
                            Row {
                                Button(onClick = {
                                    navController.navigate("details/${item.id}")
                                }) {
                                    Text(text = "See details")
                                }

                                Spacer(modifier = Modifier.width(5.dp))

                                Button(onClick = {
                                    runBlocking {
                                        listSpaceCraftViewModel.deleteSpaceCraftById(item.id)
                                        navController.navigate("list")
                                    }
                                }) {
                                    Text(text = "Delete")
                                }

                                Spacer(modifier = Modifier.width(5.dp))

                                Button(onClick = {
                                    navController.navigate("update/${item.id}")
                                    println(item.id)


                                }) {
                                    Text(text = "Update")
                                }
                            }
                        }
                    }
                    num += 1
                }
            }
        }
    }
}
