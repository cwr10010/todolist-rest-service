package todo.todolist

import todo.hexarch.Command
import todo.hexarch.CommandHandler
import todo.hexarch.Query
import todo.hexarch.QueryHandler

class CreateTodoListCommand (
        var name: String
) : Command

class ListTodoListQuery () : Query

class CreateTodoListCommandHandler (val todoLists: TodoListsService) : CommandHandler {

    override fun handleCommand(command: Command) {
        command as CreateTodoListCommand
        todoLists.addItem(TodoList(name = command.name))
    }

}

class ListTodoListQueryHandler(val todoLists: TodoListsService): QueryHandler {

    override fun handleQuery(query: Query): List<TodoList> {
        query as ListTodoListQuery
        return todoLists.allItems()
    }

}