package todo.cqrs

import todo.hexarch.Command
import todo.hexarch.CommandHandler

interface CommandBus {
    fun handleCommand(command: Command): Any?
}

class CommandBusAdapter : CommandBus {

    private val commandHandlers: MutableMap<String, CommandHandler> = mutableMapOf()

    fun addCommandHandler(commandName: String, commandHandler: CommandHandler) {
        commandHandlers[commandName] = commandHandler
    }

    override fun handleCommand(command: Command): Any? {

        return (commandHandlers[command.javaClass.name] ?: error("No command handler found")).handleCommand(command)

    }

}