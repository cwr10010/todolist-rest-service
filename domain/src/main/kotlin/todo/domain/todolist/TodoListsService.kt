package todo.domain.todolist

import todo.hexarch.DomainService

/**
 * Domain service that contains all business logic pertaining To do lists
 */
@DomainService
class TodoListsService(val todoLists: TodoListsPort) {

    fun addItem(todoList: TodoList) {
        todoLists.addTodoList(todoList)
    }

    fun allItems(): List<TodoList> {
        return todoLists.allTodoLists()
    }

    fun findItem(id: String): TodoList? {
        return todoLists.findTodoList(id)
    }

}