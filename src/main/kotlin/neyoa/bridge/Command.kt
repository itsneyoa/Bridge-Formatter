package neyoa.bridge

import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.util.BlockPos

class Command : CommandBase() {
    companion object {
        const val baseCommand = "chatbridge"
        val aliases = mutableListOf() // Remove "cb" as it's a command built in to skyblock 
    }

    override fun getCommandName() = baseCommand
    override fun getCommandAliases() = aliases

    override fun getCommandUsage(sender: ICommandSender) = "/chatbridge"

    override fun getRequiredPermissionLevel() = 0

    override fun processCommand(sender: ICommandSender, args: Array<String>) {
        if(args.isEmpty()) return Bridge.config.open()

        return when (args[0]) {
            "options" -> Bridge.chat.showConfigOptions()
            else -> Bridge.config.open()
        }
    }

    override fun addTabCompletionOptions(sender: ICommandSender, args: Array<String>, blockPos: BlockPos): MutableList<String> {
        return if(args.size == 1) mutableListOf("options")
        else mutableListOf()
    }
}