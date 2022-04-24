package si.budimir.pressureactions.commands.subcommands

import com.google.gson.Gson
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.commands.SubCommandBase
import si.budimir.pressureactions.config.data.PlateData
import si.budimir.pressureactions.enums.Permission
import si.budimir.pressureactions.utils.MessageHelper
import java.time.Instant

class GiveSubCommand(private val plugin: PressureActionsMain): SubCommandBase {
    override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        sender as Player // Only player can execute the command

        val useConsole = args[1].toBoolean()
        val plateCommand = args.drop(2).joinToString(" ")
        val plate = ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 1)
        val plateData = PlateData(plateCommand, sender.name, Instant.now().epochSecond, "null", useConsole)

        plate.editMeta { iMeta ->
            iMeta.displayName(MessageHelper.parseString("<gold>Pressure Actions Plate"))

            val lore = listOf(
                "Command: ${plateData.command}",
                "Execute as console: ${plateData.useConsole}",
                "Created: ${plateData.placedTime}",
                "Creator: ${plateData.itemOwner}",
            )

            iMeta.lore(lore.map { MessageHelper.parseString(it) })
            iMeta.persistentDataContainer.set(plugin.actionPlateKey, PersistentDataType.STRING, Gson().toJson(plateData))
        }

        sender.inventory.addItem(plate)
        sender.updateInventory()

        val placeholders = hashMapOf("command" to plateCommand)
        MessageHelper.sendMessage(sender, plugin.langConfig.plateGiven, placeholders)
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<String>
    ): List<String> {
        return when {
            args[1] == "" -> {
                listOf("<use console> <command>")
            }
            args.size == 2 -> {
                listOf("true", "false").filter { it.contains(args[1], ignoreCase = true) }
            }
            else -> {
                listOf("<command>").filter { it.contains(args[2], ignoreCase = true) }
            }
        }
    }

    override fun getPermission(): String {
        return Permission.CREATE.getPerm()
    }

    override fun getDesc(): String {
        TODO("Not yet implemented")
    }

    override fun canConsoleUse(): Boolean = false
}