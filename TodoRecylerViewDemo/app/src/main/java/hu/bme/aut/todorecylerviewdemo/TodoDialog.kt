package hu.bme.aut.todorecylerviewdemo

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import hu.bme.aut.todorecylerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_dialog.view.*
import java.util.*


class TodoDialog : DialogFragment() {

    interface TodoHandler{
        fun todoCreated(todo: Todo)
    }

    lateinit var todoHandler: TodoHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TodoHandler){
            todoHandler = context
        } else {
            throw RuntimeException(
                "The Activity is not implementing the TodoHandler interface.")
        }
    }

    lateinit var etTodoText: EditText
    lateinit var cbTodoDone: CheckBox


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogBuilder = AlertDialog.Builder(requireContext())

        dialogBuilder.setTitle("Todo dialog")
        val dialogView = requireActivity().layoutInflater.inflate(
            R.layout.todo_dialog, null
        )

        etTodoText = dialogView.etTodoText
        cbTodoDone = dialogView.cbTodoDone

        dialogBuilder.setView(dialogView)


        dialogBuilder.setPositiveButton("Ok") {
                dialog, which ->

            todoHandler.todoCreated(
                Todo(
                    null,
                    Date(System.currentTimeMillis()).toString(),
                    cbTodoDone.isChecked,
                    etTodoText.text.toString())
            )
        }
        dialogBuilder.setNegativeButton("Cancel") {
                dialog, which ->
        }


        return dialogBuilder.create()
    }

    override fun onResume() {
        super.onResume()

        etTodoText.requestFocus()

        val inputMethodManager =
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            0
        )
    }


}