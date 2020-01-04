package todo.todolist

data class TodoList(var id: String? = null, var name: String)

interface TodoListsPort {

    fun allTodoLists(): List<TodoList>

    fun findTodoList(id: String) : TodoList

    fun addTodoList(todoList: TodoList)
}