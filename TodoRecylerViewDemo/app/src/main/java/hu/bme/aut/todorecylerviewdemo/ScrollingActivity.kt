package hu.bme.aut.todorecylerviewdemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import hu.bme.aut.todorecylerviewdemo.adapter.TodoAdapter
import hu.bme.aut.todorecylerviewdemo.data.AppDatabase
import hu.bme.aut.todorecylerviewdemo.data.Todo
import hu.bme.aut.todorecylerviewdemo.touch.TodoReyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import java.util.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {

    companion object {
        const val PREF_SETTINGS = "PREF_SETTINGS"
        const val KEY_LAST_OPENED = "KEY_LAST_OPENED"
        const val KEY_FIRST_START = "KEY_FIRST_START"
    }

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        initRecyclerView()

        fab.setOnClickListener {
            var dialogTodo = TodoDialog()
            dialogTodo.isCancelable = false
            dialogTodo.show(supportFragmentManager, "Dialog")
        }

        if (isFirstStart()) {
            MaterialTapTargetPrompt.Builder(this)
                .setTarget(R.id.fab)
                .setPrimaryText("Create todo")
                .setSecondaryText("Click here to create new todo")
                .show()
        }

        saveData()
    }

    private fun saveData() {
        val sp = getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(KEY_LAST_OPENED, Date(System.currentTimeMillis()).toString())
        editor.putBoolean(KEY_FIRST_START, false)
        editor.commit()
    }

    private fun isFirstStart() : Boolean {
        val sp = getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE)
        val lastOpened = sp.getString(KEY_LAST_OPENED, "This is the first time")
        Toast.makeText(this, lastOpened, Toast.LENGTH_LONG).show()
        return sp.getBoolean(KEY_FIRST_START, true)
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
            var newId = AppDatabase.getInstance(this@ScrollingActivity).todoDao().insertTodo(todo)
            todo.todoId = newId
            runOnUiThread {
                todoAdapter.addTodo(todo)
            }
        }.start()
    }


}