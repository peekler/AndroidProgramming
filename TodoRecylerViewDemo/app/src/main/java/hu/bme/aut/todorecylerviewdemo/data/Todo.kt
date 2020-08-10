package hu.bme.aut.todorecylerviewdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) var todoId: Long?,
    @ColumnInfo(name = "createDate") var createDate: String,
    @ColumnInfo(name = "done") var done: Boolean,
    @ColumnInfo(name = "todoText") var todoText: String
)

