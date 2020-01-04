package todo.domain.todolist

import todo.hexarch.Command
import todo.hexarch.CommandHandler
import todo.hexarch.Query
import todo.hexarch.QueryHandler

/**
 * Command that tells the business part to create a new To do list
 */
class CreateTodoListCommand (
        var id: String,
        var name: String
) : Command

/**
 * Query that asks for all existing To do lists
 */
class ListTodoListQuery () : Query

/**
 * Query that asks for a specific To do list defined by its id
 */
class FindTodoListQuery (var id: String) : Query

/**
 * Handler that receives the CreateTodoListCommand and connects it to the business domain
 */
class CreateTodoListCommandHandler (val todoLists: TodoListsService) : CommandHandler {
    override fun handleCommand(command: Command) {
        command as CreateTodoListCommand
        todoLists.addItem(TodoList(id = command.id, name = command.name))
    }
}

/**
 * Handler that receives the ListTodoListQuery and connects it to the business domain
 */
class ListTodoListQueryHandler(val todoLists: TodoListsService): QueryHandler {
    override fun handleQuery(query: Query): List<TodoList> {
        query as ListTodoListQuery
        return todoLists.allItems()
    }
}

/**
 * Handler that receives the FindTodoListQuery and connects it to the business domain
 */
class FindTodoListQueryHandler(val todoLists: TodoListsService): QueryHandler {
    override fun handleQuery(query: Query): TodoList? {
        query as FindTodoListQuery
        return todoLists.findItem(query.id)
    }
}