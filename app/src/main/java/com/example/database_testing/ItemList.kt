package com.example.database_testing

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database_testing.ItemAdapter
import com.example.database_testing.ItemViewModel
import com.example.database_testing.R
import com.example.database_testing.ViewModelFactory
import com.example.database_testing.databinding.FragmentItemListBinding
import kotlinx.coroutines.launch



class ItemList : Fragment() {

    private lateinit var binding: FragmentItemListBinding

    private val viewModel: ItemViewModel by activityViewModels {
        val database = (requireActivity().application as DataBaseApp).database
        val itemDao = database.itemDao()
        ViewModelFactory(itemDao)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MyFragment", "onCreate started")
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("MyFragment", "onCreate View started")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_list, container, false)
        Log.d("MyFragment", "onCreate View started binding has finished")
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("MyFragment", "onAttach called")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("MyFragment", "onActivityCreated called")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the Adapter
        val adapter = ItemAdapter(listOf()) // Initially empty list, or viewModel.itemsList.value if it's LiveData

        // Set the Adapter to the RecyclerView
        binding.itemsRecyclerView.adapter = adapter

        // Set the LayoutManager for the RecyclerView
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(context)

        Log.d("MyFragment", "onCreated Method started: Database initialized")

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.itemsData.collect { items ->
                    Log.d("MyFragment", "ViewModel get initialized: it will be an empty list")
                    adapter.itemsData = items
                    adapter.notifyDataSetChanged()
                }
            }
        }

        binding.addItemButton.setOnClickListener {
            val navController = findNavController()
            Log.d("MyFragment", "can you click now??")
            navController.navigate(R.id.action_itemList_to_addItem)
        }
    }

}






