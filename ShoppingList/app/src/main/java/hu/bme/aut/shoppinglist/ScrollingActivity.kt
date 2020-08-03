package hu.bme.aut.shoppinglist

import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import hu.bme.aut.shoppinglist.adapter.ShoppingAdapter
import hu.bme.aut.shoppinglist.data.ShoppingItem
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity(), ShoppingItemDialog.ShoppingItemDialogHandler {

    private lateinit var adapter: ShoppingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(findViewById(R.id.toolbar))

        adapter = ShoppingAdapter(this)
        recyclerShopping.adapter = adapter

        fab.setOnClickListener {
            ShoppingItemDialog().show(supportFragmentManager, "TAG_SHOP_DIALOG")
        }

    }


    override fun shoppingItemCreated(item: ShoppingItem) {
        adapter.addItem(item)
    }

}