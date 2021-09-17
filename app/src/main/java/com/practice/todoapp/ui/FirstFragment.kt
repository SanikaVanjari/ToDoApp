package com.practice.todoapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.todoapp.MainViewModel
import com.practice.todoapp.adapter.ToDoAdapter
import com.practice.todoapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
private const val TAG = "FirstFragment"

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapterToDo: ToDoAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        adapterToDo = ToDoAdapter(onMarkComplete = {
            Toast.makeText(requireContext(), "Complete", Toast.LENGTH_SHORT).show()
        }, onDeleteClick = {
            Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
        })
        setObserver()
        setUpRV()
    }

    private fun setObserver() {
        viewModel.todoList.observe(requireActivity()) {
            Log.d(TAG, "list-- ${it.toString()}")
            it?.let { adapterToDo.todoList = it }
        }
    }

    private fun setUpRV() {
        binding.todoRV.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = adapterToDo
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}