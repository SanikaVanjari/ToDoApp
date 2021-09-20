package com.practice.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.todoapp.databinding.TodoItemLayoutBinding
import com.practice.todoapp.db.ToDo
import com.practice.todoapp.util.Actions

class ToDoAdapter(
    var click: (todo: ToDo, action: Actions) -> Unit,
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
            click: (todo: ToDo, action: Actions) -> Unit
        ) {
            binding.apply {

                titleTV.text = toDo.title
                descriptionTV.text = toDo.description

                if (toDo.complete) {
                    markCompleteBt.isVisible = false
                    updateBt.isVisible = false
                } else {
                    markCompleteBt.isVisible = true
                    updateBt.isVisible = true
                }

                markCompleteBt.setOnClickListener {
                    click(toDo, Actions.MARK_COMPLETE)
                }
                deleteBt.setOnClickListener {
                    click(toDo, Actions.DELETE)
                }
                updateBt.setOnClickListener {
                    click(toDo, Actions.UPDATE)
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
        holder.bind(todoList[position], click = { todo, action ->
            click(todo, action)
        })
    }

    override fun getItemCount(): Int = todoList.size


}