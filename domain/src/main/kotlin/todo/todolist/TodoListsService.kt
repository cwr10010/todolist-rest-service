package todo.todolist

import todo.hexarch.DomainService

@DomainService
class TodoListsService(val todoLists: TodoListsPort) {

    fun addItem(todoList: TodoList) {
        todoLists.addTodoList(todoList)
    }

    fun allItems(): List<TodoList> {
        return todoLists.allTodoLists()
    }

}