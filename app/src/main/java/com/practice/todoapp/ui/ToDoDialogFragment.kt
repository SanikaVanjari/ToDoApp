package com.practice.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.practice.todoapp.MainViewModel
import com.practice.todoapp.databinding.TodoDialogLayoutBinding
import com.practice.todoapp.db.ToDo

private const val TAG = "ToDoDialogFragment"

class ToDoDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {
    private var _binding: TodoDialogLayoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private val args: ToDoDialogFragmentArgs by navArgs()
    private var toDoForUpdate: ToDo? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TodoDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setDataFromArgs()
        binding.saveBt.setOnClickListener(this)
        binding.closeBt.setOnClickListener(this)
    }

    private fun setDataFromArgs() {
        args.todo?.let {
            toDoForUpdate = it
            binding.titleEt.text?.apply {
                clear()
                append(it?.title)
            }
            binding.descriptionEt.text?.apply {
                clear()
                append(it?.description)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.saveBt -> {
                if (isToDoNotValid()) {
                    Toast.makeText(requireContext(), "Please fill all details", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (toDoForUpdate != null) {
                        toDoForUpdate?.id?.let {
                            mainViewModel.updateToDo(
                                ToDo(
                                    id = it,
                                    title = binding.titleEt.text.toString(),
                                    description = binding.descriptionEt.text.toString()
                                )
                            )
                        }

                    } else {
                        mainViewModel.insertToDo(
                            ToDo(
                                title = binding.titleEt.text.toString(),
                                description = binding.descriptionEt.text.toString()
                            )
                        )
                    }
                    findNavController().navigateUp()
                }
            }
            binding.closeBt -> {
                findNavController().navigateUp()
            }
            else -> Unit
        }
    }

    private fun isToDoNotValid(): Boolean {
        return binding.titleEt.text.isNullOrEmpty() || binding.descriptionEt.text.isNullOrEmpty()
    }

}