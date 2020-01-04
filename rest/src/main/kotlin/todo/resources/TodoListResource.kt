package todo.resources

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import todo.cqrs.CommandBus
import todo.cqrs.QueryBus
import todo.todolist.ListTodoListQuery

@RestController
@RequestMapping("todo/")
class TodoListResource(val commandBus: CommandBus, val queryBus: QueryBus) {

    @GetMapping
    fun index(): Any? {

        return queryBus.handleQuery(ListTodoListQuery())
    }
}