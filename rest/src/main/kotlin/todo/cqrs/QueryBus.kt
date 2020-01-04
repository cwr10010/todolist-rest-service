package todo.cqrs

import todo.hexarch.Query
import todo.hexarch.QueryHandler

/**
 * Functional Interface that represents the capability of a Query Bus to handle queries
 */
interface QueryBus {
    fun handleQuery(query: Query): Any?
}

/**
 * Implementation of a QueryBus. It holds all available query handlers and dispatches the query to the related
 * handler based on their name. This is a very simple implementation that omits usage of a Spring Event Handler.
 */
class QueryBusAdapter : QueryBus {

    private val queryHandlers: MutableMap<String, QueryHandler> = mutableMapOf()

    fun addQueryHandler(queryName: String, queryHandler: QueryHandler) {
        queryHandlers[queryName] = queryHandler
    }

    override fun handleQuery(query: Query): Any? {
        return (queryHandlers[query.javaClass.simpleName] ?: error("No query handler found " + query.javaClass.simpleName)).handleQuery(query)
    }

}