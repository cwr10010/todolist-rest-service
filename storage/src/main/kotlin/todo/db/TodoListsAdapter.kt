package todo.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import todo.domain.todolist.TodoListsPort
import todo.domain.todolist.TodoList
import javax.persistence.*

/**
 * DB Entity that represents a To do list
 */
@Entity
data class TodoListEntity(
        @Id
        var id: String? = null,

        @Column(nullable = false)
        var name: String? = null
)

/**
 * JPA repository that handles To do lists
 */
interface TodoListRepository : JpaRepository<TodoListEntity, String>

/**
 * The adapter to the port of the domain port for handling To do lists. It encapsules the database handling.
 */
@Repository
class TodoListDbAdapter(val todoLists: TodoListRepository) : TodoListsPort {

    override fun findTodoList(id: String) : TodoList? {
        return todoLists.findById(id).map { entity ->
            TodoList(entity.id!!, entity.name!!)
        }.orElse(null)
    }

    override fun allTodoLists(): List<TodoList> {
        return todoLists.findAll()
                .map { todoListEntity ->
                    TodoList(todoListEntity.id!!, todoListEntity.name!!)
                }
    }

    override fun addTodoList(todoList: TodoList) {
        todoLists.save(TodoListEntity(todoList.id, todoList.name))
    }
}
