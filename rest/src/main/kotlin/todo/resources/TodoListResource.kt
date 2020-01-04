package todo.resources

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import todo.cqrs.CommandBus
import todo.cqrs.QueryBus
import todo.domain.todolist.CreateTodoListCommand
import todo.domain.todolist.FindTodoListQuery
import todo.domain.todolist.ListTodoListQuery
import todo.domain.todolist.TodoList
import java.util.UUID

/**
 * Rest resource for To do lists. It sports Hateoas navigation information.
 */
@RestController
@RequestMapping("todo/")
class TodoListResource(val commandBus: CommandBus, val queryBus: QueryBus) {

    @GetMapping
    fun index(): Any {
        @Suppress("UNCHECKED_CAST") val resultList: List<TodoList> = queryBus.handleQuery(ListTodoListQuery()) as List<TodoList>
        return resultList.map { result:TodoList ->
            TodoListResultVO(result.id, result.name)
        }. let { result ->
            ResponseEntity.ok(CollectionModel(result, linkTo(methodOn(this.javaClass).index()).withSelfRel()))
        }
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable("id") id: String): ResponseEntity<EntityModel<TodoListResultVO>> {
         return queryBus.handleQuery(FindTodoListQuery(id)).let { result ->
             if (result != null) {
                 (result as TodoList).let {
                     ResponseEntity.ok(EntityModel(TodoListResultVO(it.id, it.name),
                             linkTo(methodOn(this.javaClass).findOne(id)).withSelfRel(),
                             linkTo(methodOn(this.javaClass).index()).withRel("")))
                 }
             } else {
                 ResponseEntity.notFound().build()
             }
        }
    }

    @PutMapping
    fun create(createTodoListVO: CreateTodoListVO): ResponseEntity<Void> {
        val id = UUID.randomUUID().toString()
        commandBus.handleCommand(CreateTodoListCommand(id, createTodoListVO.name))

        val link = linkTo(methodOn(this.javaClass).findOne(id)).withSelfRel()
        return ResponseEntity.noContent().location(link.toUri()).build<Void>()
    }
}

data class CreateTodoListVO(var name: String)

data class TodoListResultVO(var id: String, var name: String)
