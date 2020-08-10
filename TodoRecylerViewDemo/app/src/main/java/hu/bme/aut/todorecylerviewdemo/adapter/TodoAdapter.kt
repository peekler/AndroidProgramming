package hu.bme.aut.todorecylerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.todorecylerviewdemo.R
import hu.bme.aut.todorecylerviewdemo.ScrollingActivity
import hu.bme.aut.todorecylerviewdemo.data.AppDatabase
import hu.bme.aut.todorecylerviewdemo.data.Todo
import hu.bme.aut.todorecylerviewdemo.touch.TodoTouchHelperCallback
import kotlinx.android.synthetic.main.todo_row.view.*
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>, TodoTouchHelperCallback{

    var todoItems = mutableListOf<Todo>()

    val context: Context
    constructor(context: Context, todoList: List<Todo>) : super() {
        this.context = context
        todoItems.addAll(todoList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoView = LayoutInflater.from(context).inflate(R.layout.todo_row,
            parent, false)
        return ViewHolder(todoView)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var todoItem = todoItems[position]

        holder.tvDate.text = todoItem.createDate
        holder.cbDone.isChecked = todoItem.done
        holder.cbDone.text = todoItem.todoText

        holder.btnDel.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }

        holder.cbDone.setOnClickListener {
            todoItem.done = holder.cbDone.isChecked

            Thread {
                AppDatabase.getInstance(context).todoDao().updateTodo(todoItem)
            }.start()
        }
    }

    fun addTodo(todo: Todo){
        todoItems.add(todo)
        notifyItemInserted(todoItems.lastIndex)
    }

    fun deleteTodo(position: Int) {
        try {
            var todoToDelete = todoItems.get(position)
            Thread {
                AppDatabase.getInstance(context).todoDao().deleteTodo(todoToDelete)

                (context as ScrollingActivity).runOnUiThread {
                    todoItems.removeAt(position)
                    notifyItemRemoved(position)
                }
            }.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDismissed(position: Int) {
        deleteTodo(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvDate = itemView.tvDate
        var cbDone = itemView.cbDone
        var btnDel = itemView.btnDel
    }


}