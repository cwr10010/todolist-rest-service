package todo.db

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import todo.todolist.TodoListsPort
import todo.todolist.TodoList
import java.util.*
import javax.persistence.*

@Entity
data class TodoListEntity(
        @Id
        var id: String? = null,

        @Column(nullable = false)
        var name: String? = null)

interface TodoListRepository : JpaRepository<TodoListEntity, String>

@Repository
class TodoListDbAdapter(val todoLists: TodoListRepository) : TodoListsPort {

    override fun findTodoList(id: String) : TodoList {
        return todoLists.findById(id)
                .map { todoListEntity ->
                    TodoList(todoListEntity.id, todoListEntity.name!!)
                }
                .orElseThrow {
                    TodoListNotFoundException("NOT FOUND")
                }
    }

    override fun allTodoLists(): List<TodoList> {
        return todoLists.findAll()
                .map { todoListEntity ->
                    TodoList(todoListEntity.id, todoListEntity.name!!)
                }
    }

    override fun addTodoList(todoList: TodoList) {
        todoLists.save(TodoListEntity(UUID.randomUUID().toString(), todoList.name))
    }
}

class TodoListNotFoundException(message: String?): RuntimeException(message)