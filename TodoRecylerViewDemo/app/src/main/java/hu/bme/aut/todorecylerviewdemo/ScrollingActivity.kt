package hu.bme.aut.todorecylerviewdemo

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import hu.bme.aut.todorecylerviewdemo.adapter.TodoAdapter
import hu.bme.aut.todorecylerviewdemo.data.AppDatabase
import hu.bme.aut.todorecylerviewdemo.data.Todo
import hu.bme.aut.todorecylerviewdemo.touch.TodoReyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        initRecyclerView()

        fab.setOnClickListener {
            TodoDialog().show(supportFragmentManager, "Dialog")
        }

    }

    private fun initRecyclerView() {
        Thread {
            var todoList = AppDatabase.getInstance(this@ScrollingActivity).todoDao().getAllTodos()

            runOnUiThread {
                todoAdapter = TodoAdapter(this, todoList)
                recyclerTodo.adapter = todoAdapter
                //recyclerTodo.layoutManager = GridLayoutManager(this, 2)

                val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
                recyclerTodo.addItemDecoration(itemDecoration)

                val touchCallback = TodoReyclerTouchCallback(todoAdapter)
                val itemTouchHelper = ItemTouchHelper(touchCallback)
                itemTouchHelper.attachToRecyclerView(recyclerTodo)
            }

        }.start()


    }

    override fun todoCreated(todo: Todo) {
        Thread {
            AppDatabase.getInstance(this@ScrollingActivity).todoDao().insertTodo(todo)

            runOnUiThread {
                todoAdapter.addTodo(todo)
            }
        }.start()
    }


}