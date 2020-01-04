package todo.cqrs

import todo.hexarch.Command
import todo.hexarch.CommandHandler

/**
 * Functional Interface that represents the capability of a Command Bus to handle commands
 */
interface CommandBus {
    fun handleCommand(command: Command): Any?
}

/**
 * Implementation of a CommandBus. It holds all available command handlers and dispatches the commands to the related
 * handler based on their name. This is a very simple implementation that omits usage of a Spring Event Handler.
 */
class CommandBusAdapter : CommandBus {

    private val commandHandlers: MutableMap<String, CommandHandler> = mutableMapOf()

    fun addCommandHandler(commandName: String, commandHandler: CommandHandler) {
        commandHandlers[commandName] = commandHandler
    }

    override fun handleCommand(command: Command): Any? {
        return (commandHandlers[command.javaClass.simpleName] ?: error("No command handler found " + command.javaClass.simpleName)).handleCommand(command)
    }

}