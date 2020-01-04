package todo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType.ANNOTATION
import todo.cqrs.CommandBus
import todo.cqrs.CommandBusAdapter
import todo.cqrs.QueryBus
import todo.cqrs.QueryBusAdapter
import todo.hexarch.DomainService
import todo.todolist.CreateTodoListCommandHandler
import todo.todolist.ListTodoListQueryHandler
import todo.todolist.TodoListsService

@Configuration
@ComponentScan(
        basePackages = ["todo"],
        includeFilters = [Filter(type = ANNOTATION, value = [DomainService::class])])
class DomainConfiguration {

    @Bean
    fun commandBus(todoLists: TodoListsService) : CommandBus {

        val commandBus = CommandBusAdapter()
        commandBus.addCommandHandler("CreateTodoListCommand", CreateTodoListCommandHandler(todoLists))
        return commandBus
    }

    @Bean
    fun queryBus(todoLists: TodoListsService) : QueryBus {

        val queryBus = QueryBusAdapter()
        queryBus.addQueryHandler("ListTodoListQuery", ListTodoListQueryHandler(todoLists))
        return queryBus
    }
}