package todo.cqrs

import todo.hexarch.Query
import todo.hexarch.QueryHandler

interface QueryBus {
    fun handleQuery(query: Query): Any?
}

class QueryBusAdapter : QueryBus {

    private val queryHandlers: MutableMap<String, QueryHandler> = mutableMapOf()

    fun addQueryHandler(queryName: String, queryHandler: QueryHandler) {
        queryHandlers[queryName] = queryHandler
    }

    override fun handleQuery(query: Query): Any? {

        return (queryHandlers[query.javaClass.simpleName] ?: error("No query handler found " + query.javaClass.simpleName)).handleQuery(query)

    }

}