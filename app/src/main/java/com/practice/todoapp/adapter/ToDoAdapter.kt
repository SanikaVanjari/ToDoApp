package com.practice.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.todoapp.databinding.TodoItemLayoutBinding
import com.practice.todoapp.db.ToDo

class ToDoAdapter(
    var onMarkComplete: (todo: ToDo) -> Unit,
    var onDeleteClick: (todo: ToDo) -> Unit,
    var onUpdateClick: (todo: ToDo) -> Unit
) :
    RecyclerView.Adapter<ToDoAdapter.ToDoListViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    val differ: AsyncListDiffer<ToDo> = AsyncListDiffer(this, diffCallBack)

    var todoList: List<ToDo>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    class ToDoListViewHolder(private val binding: TodoItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            toDo: ToDo,
            onMarkComplete: (todo: ToDo) -> Unit,
            onDeleteClick: (todo: ToDo) -> Unit,
            onUpdateClick: (todo: ToDo) -> Unit
        ) {
            binding.apply {
                titleTV.text = toDo.title
                descriptionTV.text = toDo.description
                markCompleteBt.setOnClickListener {
                    onMarkComplete(toDo)
                }
                deleteBt.setOnClickListener {
                    onDeleteClick(toDo)
                }
                updateBt.setOnClickListener {
                    onUpdateClick(toDo)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder(
            TodoItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.bind(todoList[position], onMarkComplete = {
            onMarkComplete(it)
        }, onDeleteClick = {
            onDeleteClick(it)
        }, onUpdateClick = {
            onUpdateClick(it)
        })
    }

    override fun getItemCount(): Int = todoList.size


}