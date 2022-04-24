package si.budimir.pressureactions.commands.subcommands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.commands.SubCommandBase
import si.budimir.pressureactions.enums.Permission
import si.budimir.pressureactions.utils.LocationUtils
import si.budimir.pressureactions.utils.MessageHelper
import java.time.Instant
import java.time.ZoneId

class InfoSubCommand(private val plugin: PressureActionsMain): SubCommandBase {
    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        sender as Player

        val loc = sender.getTargetBlock(null, 10).location
        val plateCommand = plugin.mainConfig.configuredPlates[LocationUtils.locToString(loc)]

        if (plateCommand == null) {
            MessageHelper.sendMessage(sender, plugin.langConfig.plateNotFound)
            return true
        }

        val infoMessage = arrayListOf<String>()
        infoMessage.add("-=+=--=+=--=+=--=+=-[<green>${loc.blockX}<gray>, <green>${loc.blockY}<gray>, <green>${loc.blockZ}<gray>,]-=+=--=+=--=+=--=+=-")
        infoMessage.add(" <gold>• Command: <gray>${plateCommand.command}")
        infoMessage.add(" <gold>• Use Console: <gray>${plateCommand.useConsole}")
        infoMessage.add(" <gold>• Placed Time: <gray>${Instant.ofEpochSecond(plateCommand.placedTime).atZone(ZoneId.systemDefault())}")
        infoMessage.add(" <gold>• Item owner: <gray>${plateCommand.itemOwner}")
        infoMessage.add(" <gold>• Placed by: <gray>${plateCommand.placedBy}")
        infoMessage.add("-=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=-")

        MessageHelper.sendMessage(sender, infoMessage.joinToString("<newline>"))
        return true
    }

    override fun getPermission(): String {
        return Permission.INFO.getPerm()
    }

    override fun getDesc(): String {
        TODO("Not yet implemented")
    }

    override fun canConsoleUse(): Boolean = false
}