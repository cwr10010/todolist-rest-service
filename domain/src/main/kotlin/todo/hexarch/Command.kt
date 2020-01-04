package todo.hexarch

interface Command

interface CommandHandler {

    fun handleCommand(command: Command)
}
