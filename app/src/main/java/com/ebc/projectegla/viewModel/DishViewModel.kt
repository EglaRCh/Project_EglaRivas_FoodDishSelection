package com.ebc.projectegla.viewModel

import androidx.lifecycle.ViewModel
import com.ebc.projectegla.data.Data
import com.ebc.projectegla.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class DishState(
   val filteredDishes: List<Dish> = emptyList(),
   val totalPrice: Double = 0.0
)

class DishViewModel : ViewModel() {

   private val _state = MutableStateFlow(DishState())
   val state: StateFlow<DishState> = _state

   private var selectedDishes = mutableSetOf<Dish>()

   fun filterDishes(isCold: Boolean?, isCaldoso: Boolean?, isSalty: Boolean?, cuisine: String) {
      val filtered = Data.getAllDishes().filter { dish ->
         (isCold == null || dish.isCold == isCold) &&
                 (isCaldoso == null || dish.isCaldoso == isCaldoso) &&
                 (isSalty == null || dish.isSalty == isSalty) &&
                 (cuisine.isBlank() || dish.cuisine.contains(cuisine, ignoreCase = true))
      }
      _state.value = _state.value.copy(filteredDishes = filtered)
   }

   fun toggleDishSelection(dish: Dish) {
      if (selectedDishes.contains(dish)) {
         selectedDishes.remove(dish)
      } else {
         selectedDishes.add(dish)
      }
      updateTotalPrice()
   }

   fun resetSearch() {
      selectedDishes.clear()
      _state.value = DishState(totalPrice = 0.0)
   }

   private fun updateTotalPrice() {
      val total = selectedDishes.sumOf { it.price }
      _state.value = _state.value.copy(totalPrice = total)
   }
}
