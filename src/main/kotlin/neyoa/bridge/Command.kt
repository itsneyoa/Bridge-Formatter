package neyoa.bridge

import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender

class Command : CommandBase() {
    companion object {
        const val baseCommand = "chatbridge"
        val aliases = mutableListOf("cb")
    }

    override fun getCommandName() = baseCommand
    override fun getCommandAliases() = aliases

    override fun getCommandUsage(sender: ICommandSender) = "/chatbridge"

    override fun getRequiredPermissionLevel() = 0

    private fun openGui() = run { Bridge.gui = Config.gui() }

    override fun processCommand(sender: ICommandSender, args: Array<String>) = openGui()
}