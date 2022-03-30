package com.example.browsekittys.ui.choose

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.browsekittys.R
import com.example.browsekittys.SharedViewModel
import com.example.browsekittys.databinding.ChooseFragmentBinding

class Choose : Fragment() {


    private lateinit var viewModel: ChooseViewModel
    private lateinit var binding: ChooseFragmentBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var categoryId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChooseFragmentBinding.inflate(this.layoutInflater)
        viewModel = ViewModelProvider(this)[ChooseViewModel::class.java]

        val items = viewModel.getItems()
        val categoryItems = viewModel.categoryItems
        val itemNumberAdapter = ArrayAdapter(requireContext(), R.layout.list_of_items, items)
        val categoryAdapter =
            ArrayAdapter(requireContext(), R.layout.list_of_categories, categoryItems)
        (binding.itemTextField.editText as? AutoCompleteTextView)?.setAdapter(itemNumberAdapter)
        (binding.categoryTextField.editText as? AutoCompleteTextView)?.setAdapter(categoryAdapter)
        binding.apply {
            itemTextField.editText?.addTextChangedListener {
                if (it!!.isNotBlank() && it.toString().toInt() > 100){
                    itemTextField.error = "more than 100 "
                }else {
                    itemTextField.error = null
                }

            }
            categoryTextField.editText?.setOnClickListener {
                it.hideKeyboard()
            }
            button.setOnClickListener {
                if (binding.itemTextField.editText?.text.toString() != "") {
                    categoryId = when (categoryTextField.editText?.text.toString()) {
                        "boxes" -> 5
                        "clothes" -> 15
                        "hats" -> 1
                        "sinks" -> 14
                        "space" -> 2
                        "sunglasses" -> 4
                        else -> 7
                    }
                    sharedViewModel.setData(
                        binding.itemTextField.editText?.text.toString().toInt(),
                        categoryId
                    )
                    if (itemTextField.editText?.text.toString() !== "" && itemTextField.editText?.text.toString()
                            .toInt() <= 100
                    ) {
//                        findNavController().navigate(
//                            ChooseDirections.actionChooseToPets(
//                                binding.itemTextField.editText?.text.toString().toInt(), categoryId
//                            )
//                        )
                        findNavController().navigate(R.id.action_choose_to_pets)
                        itemTextField.error = "more than 100 not allowed"
                    } else if (itemTextField.editText?.text.toString().toInt() > 100) {
                        itemTextField.error = "more than 100 not allowed"
                    } else if (itemTextField.editText?.text!!.count() == 0){
                        itemTextField.error = "provide input"
                    }
                }
            }
        }
        return binding.root
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}
