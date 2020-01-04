package todo.hexarch

/**
 * Marker interface that defines a class as a Command
 */
interface Command

/**
 * Marker interface that defines a class as a CommandHandler
 */
interface CommandHandler {

    fun handleCommand(command: Command)
}
