package com.ebc.projectegla.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ebc.projectegla.viewModel.DishViewModel

@Composable
fun MainScreen(viewModel: DishViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    var isCold by remember { mutableStateOf<Boolean?>(null) }
    var isCaldoso by remember { mutableStateOf<Boolean?>(null) }
    var isSalty by remember { mutableStateOf<Boolean?>(null) }
    var cuisine by remember { mutableStateOf<String>("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // UI for filtering

        // Is Cold Dropdown
        var isColdExpanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { isColdExpanded = true }) {
                Text(if (isCold == null) "Temperatura" else if (isCold == true) "Frío" else "Caliente")
            }
            DropdownMenu(
                expanded = isColdExpanded,
                onDismissRequest = { isColdExpanded = false },
            ) {
                DropdownMenuItem(
                    text = { Text("Frío", color = Color.Black) },
                    onClick = {
                        isCold = true
                        isColdExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
                DropdownMenuItem(
                    text = { Text("Caliente", color = Color.Black) },
                    onClick = {
                        isCold = false
                        isColdExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Is Caldoso Dropdown
        var isCaldosoExpanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { isCaldosoExpanded = true }) {
                Text(if (isCaldoso == null) "Consistencia" else if (isCaldoso == true) "Caldoso" else "Sólido")
            }
            DropdownMenu(
                expanded = isCaldosoExpanded,
                onDismissRequest = { isCaldosoExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Caldoso", color = Color.Black) },
                    onClick = {
                        isCaldoso = true
                        isCaldosoExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
                DropdownMenuItem(
                    text = { Text("Sólido", color = Color.Black) },
                    onClick = {
                        isCaldoso = false
                        isCaldosoExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Is Salty Dropdown
        var isSaltyExpanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { isSaltyExpanded = true }) {
                Text(if (isSalty == null) "Sabor" else if (isSalty == true) "Salado" else "Dulce")
            }
            DropdownMenu(
                expanded = isSaltyExpanded,
                onDismissRequest = { isSaltyExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Salado", color = Color.Black) },
                    onClick = {
                        isSalty = true
                        isSaltyExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
                DropdownMenuItem(
                    text = { Text("Dulce", color = Color.Black) },
                    onClick = {
                        isSalty = false
                        isSaltyExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Cuisine Dropdown
        var cuisineExpanded by remember { mutableStateOf(false) }
        Box {
            Button(onClick = { cuisineExpanded = true }) {
                Text(cuisine.ifBlank { "Tipo de Cocina" })
            }
            DropdownMenu(
                expanded = cuisineExpanded,
                onDismissRequest = { cuisineExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Mexicana", color = Color.Black) },
                    onClick = {
                        cuisine = "Mexicana"
                        cuisineExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
                DropdownMenuItem(
                    text = { Text("Japonesa", color = Color.Black) },
                    onClick = {
                        cuisine = "Japonesa"
                        cuisineExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
                DropdownMenuItem(
                    text = { Text("Italiana", color = Color.Black) },
                    onClick = {
                        cuisine = "Italiana"
                        cuisineExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
                DropdownMenuItem(
                    text = { Text("China", color = Color.Black) },
                    onClick = {
                        cuisine = "China"
                        cuisineExpanded = false
                    },
                    modifier = Modifier.background(Color.LightGray).fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Button
        Button(
            onClick = { viewModel.filterDishes(isCold, isCaldoso, isSalty, cuisine) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Buscar", color = Color.White)
        }

        // Reset Button
        Button(
            onClick = {
                isCold = null
                isCaldoso = null
                isSalty = null
                cuisine = ""
                viewModel.resetSearch()
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Nueva Búsqueda")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // List of Filtered Dishes
        LazyColumn {
            items(state.filteredDishes) { dish ->
                var isSelected by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(dish.name)
                    Text(dish.price.toString())
                    Checkbox(
                        checked = isSelected,
                        onCheckedChange = {
                            isSelected = it
                            viewModel.toggleDishSelection(dish)
                        }
                    )
                }
            }
        }

        // Total Price
        Text(
            "Total: $${state.totalPrice}",
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF7043),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
