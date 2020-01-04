package todo.hexarch

interface Query

interface QueryHandler {

    fun handleQuery(query: Query): Any
}
