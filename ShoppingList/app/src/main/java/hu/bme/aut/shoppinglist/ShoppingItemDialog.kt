package hu.bme.aut.shoppinglist

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.shoppinglist.data.ShoppingItem
import kotlinx.android.synthetic.main.shopping_dialog.view.*

class ShoppingItemDialog : DialogFragment() {

    interface ShoppingItemDialogHandler {
        fun shoppingItemCreated(item: ShoppingItem)
    }

    private lateinit var shoppingItemHandler: ShoppingItemDialogHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ShoppingItemDialogHandler) {
            shoppingItemHandler = context
        } else {
            throw RuntimeException("The Activity does not implement the ShoppingItemDialogHandler interface")
        }
    }

    private lateinit var etName: EditText
    private lateinit var etPrice: EditText
    private lateinit var spinnerCategory: Spinner

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("New Item")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.shopping_dialog, null)

        etName = rootView.etName
        etPrice = rootView.etPrice
        spinnerCategory = rootView.spinnerCategory
        var categoryAdapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        )
        categoryAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerCategory.adapter = categoryAdapter

        builder.setView(rootView)

        builder.setPositiveButton("OK") { dialog, which ->
            //... keep empty
        }
        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val dialog = dialog as AlertDialog
        val positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE)

        positiveButton.setOnClickListener {
            if (etName.text.isNotEmpty()) {
                if (etPrice.text.isNotEmpty()) {
                    handleItemCreate()
                    dialog.dismiss()
                } else {
                    etPrice.error = "This field can not be empty"
                }
            } else {
                etName.error = "This field can not be empty"
            }
        }
    }



    fun handleItemCreate() {
        shoppingItemHandler.shoppingItemCreated(
            ShoppingItem(etName.text.toString(),
            etPrice.text.toString().toInt(),
            spinnerCategory.selectedItemPosition,
            false,
            "Demo")
        )
    }

}