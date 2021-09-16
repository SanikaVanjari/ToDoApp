package com.practice.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.practice.todoapp.databinding.TodoDialogLayoutBinding

class ToDoDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {
    private var _binding: TodoDialogLayoutBinding? = null
    private val binding get() = _binding!!

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
        binding.saveBt.setOnClickListener(this)
        binding.closeBt.setOnClickListener(this)
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