package hu.bme.aut.shoppinglist.data

data class ShoppingItem(
    var name: String,
    var price: Int,
    var category: Int,
    var bought: Boolean,
    var description: String
)