package hu.bme.aut.todorecylerviewdemo

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import hu.bme.aut.todorecylerviewdemo.adapter.TodoAdapter
import hu.bme.aut.todorecylerviewdemo.data.Todo
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        todoAdapter = TodoAdapter(this)
        recyclerTodo.adapter = todoAdapter

        fab.setOnClickListener {
            TodoDialog().show(supportFragmentManager, "Dialog")
        }

    }

    override fun todoCreated(todo: Todo) {
        todoAdapter.addTodo(todo)
    }


}