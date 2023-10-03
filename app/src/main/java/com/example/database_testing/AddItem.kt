package com.example.database_testing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.database_testing.databinding.FragmentAddItemBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddItems : Fragment() {

    private var binding: FragmentAddItemBinding ?= null

    private val viewModel: ItemViewModel by activityViewModels {
        val database = (requireActivity().application as DataBaseApp).database
        val itemDao = database.itemDao()
        ViewModelFactory(itemDao)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_item,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.button?.setOnClickListener {
            val inventoryName = binding?.userInputName?.text?.toString()?.trim()
            val price = binding?.userInputPrice?.text?.toString()?.trim()
            val quantity = binding?.userInputQuantity?.text?.toString()?.trim()

            if (inventoryName.isNullOrEmpty() || price.isNullOrEmpty() || quantity.isNullOrEmpty()) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Invalid")
                    .setMessage("Please enter all information")
                    .setPositiveButton("Ok") { dialog, _ ->
                        dialog.dismiss()
                    }.show()
            } else {
                viewModel.addNewItem(inventoryName, price, quantity)
                val nav = findNavController()
                nav.navigate(R.id.action_addItem_to_itemList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}