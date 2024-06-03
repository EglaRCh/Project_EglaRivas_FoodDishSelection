package com.ebc.projectegla.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebc.projectegla.R
import com.ebc.projectegla.viewModel.DishViewModel
import kotlinx.coroutines.launch

class DishFragment : Fragment() {

    private val viewModel: DishViewModel by viewModels()
    private lateinit var dishAdapter: DishAdapter
    private lateinit var totalTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val searchButton: Button = view.findViewById(R.id.searchButton)
        val isColdCheckBox: CheckBox = view.findViewById(R.id.isColdCheckBox)
        val isCaldosoCheckBox: CheckBox = view.findViewById(R.id.isCaldosoCheckBox)
        val isSaltyCheckBox: CheckBox = view.findViewById(R.id.isSaltyCheckBox)
        val cuisineEditText: EditText = view.findViewById(R.id.cuisineEditText)
        val newSearchButton: Button = view.findViewById(R.id.newSearchButton)
        totalTextView = view.findViewById(R.id.totalTextView)

        dishAdapter = DishAdapter { dish ->
            viewModel.toggleDishSelection(dish)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = dishAdapter

        searchButton.setOnClickListener {
            val isCold = if (isColdCheckBox.isChecked) true else null
            val isCaldoso = if (isCaldosoCheckBox.isChecked) true else null
            val isSalty = if (isSaltyCheckBox.isChecked) true else null
            val cuisine = cuisineEditText.text.toString()
            viewModel.filterDishes(isCold, isCaldoso, isSalty, cuisine)
        }

        newSearchButton.setOnClickListener {
            isColdCheckBox.isChecked = false
            isCaldosoCheckBox.isChecked = false
            isSaltyCheckBox.isChecked = false
            cuisineEditText.text.clear()
            viewModel.resetSearch()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    dishAdapter.submitList(state.filteredDishes)
                    totalTextView.text = getString(R.string.total_format, state.totalPrice)
                }
            }
        }
    }
}
