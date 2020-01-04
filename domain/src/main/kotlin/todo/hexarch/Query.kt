package todo.hexarch

/**
 * Marker interface that defines a class as a Query
 */
interface Query

/**
 * Marker interface that defines a class as a QueryHandler
 */
interface QueryHandler {

    fun handleQuery(query: Query): Any?
}
