package hu.bme.aut.todorecylerviewdemo.data

import androidx.room.*

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    fun getAllTodos(): List<Todo>

    @Insert
    fun insertTodo(vararg todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

}
