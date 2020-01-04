package todo.domain.todolist

/**
 * DTO class to transport information about a TodoList to the driver ports
 */
data class TodoList(var id: String, var name: String)

/**
 * Port for handling updates on a storage backend. It has to be implemented by an adapter there.
 */
interface TodoListsPort {

    fun allTodoLists(): List<TodoList>

    fun findTodoList(id: String) : TodoList?

    fun addTodoList(todoList: TodoList)
}