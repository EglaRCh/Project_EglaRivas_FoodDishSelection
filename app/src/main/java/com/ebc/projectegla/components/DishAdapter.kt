package com.ebc.projectegla.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ebc.projectegla.R
import com.ebc.projectegla.model.Dish

class DishAdapter(
    private val onDishClick: (Dish) -> Unit
) : RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    private var dishes: List<Dish> = emptyList()

    fun submitList(newDishes: List<Dish>) {
        dishes = newDishes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dish, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val dish = dishes[position]
        holder.bind(dish)
    }

    override fun getItemCount(): Int = dishes.size

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.dishNameTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.dishPriceTextView)

        fun bind(dish: Dish) {
            nameTextView.text = dish.name
            priceTextView.text = itemView.context.getString(R.string.price_format, dish.price)
            itemView.setOnClickListener {
                onDishClick(dish)
            }
        }
    }
}
