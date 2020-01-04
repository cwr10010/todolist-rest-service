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
import todo.domain.todolist.CreateTodoListCommandHandler
import todo.domain.todolist.FindTodoListQueryHandler
import todo.domain.todolist.ListTodoListQueryHandler
import todo.domain.todolist.TodoListsService

/**
 * Configuration class that wires the Command and Query Bus to the domain. Command Bus and Query Bus are both unaware
 * of the handler implementation and only loosely coupled via this configuration.
 */
@Configuration
@ComponentScan(
        basePackages = ["todo.domain"],
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
        queryBus.addQueryHandler("FindTodoListQuery", FindTodoListQueryHandler(todoLists))
        return queryBus
    }
}