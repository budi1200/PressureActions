package si.budimir.pressureactions.commands

import org.bukkit.command.*
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.commands.subcommands.GiveSubCommand
import si.budimir.pressureactions.commands.subcommands.InfoSubCommand
import si.budimir.pressureactions.commands.subcommands.ReloadSubCommand
import si.budimir.pressureactions.enums.Permission
import si.budimir.pressureactions.utils.MessageHelper

class PActionsCommand(private val plugin: PressureActionsMain) : CommandExecutor, TabExecutor {
    private val subCommands: MutableMap<String, SubCommandBase> = HashMap()
    private var subCommandsList: List<String> = emptyList()

    init {
        subCommands["reload"] = ReloadSubCommand(plugin)
        subCommands["give"] = GiveSubCommand(plugin)
        subCommands["info"] = InfoSubCommand(plugin)

        subCommandsList = subCommands.keys.toList()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {

        if (args.isNotEmpty()) run {
            val sc: SubCommandBase = subCommands[args[0]] ?: return false

            val reqPerm: String = sc.getPermission()
            if (reqPerm != "" && !sender.hasPermission(reqPerm)) {
                MessageHelper.sendMessage(sender, plugin.langConfig.noPermission, hashMapOf("perm" to reqPerm))
                return true
            }

            // Check if console can use the sub command
            if (sender is ConsoleCommandSender && !sc.canConsoleUse()) {
                MessageHelper.sendMessage(sender, "This command cannot be used from console.")
                return true
            }

            sc.execute(sender, command, label, args)
        } else {
            if (!sender.hasPermission(Permission.USE.getPerm())) return true

            val helpMessage = arrayListOf<String>()

            helpMessage.add("<gray>-=+=--=+=--=+=--=+=-[<gold>Plate Actions<gray>]-=+=--=+=--=+=--=+=-")
            helpMessage.add(" • <gold>/pactions give <true:false> <command><gray>- Gives yourself a pressure plate in which you can place down to execute the command. If set to true, the command will be done by console, otherwise it will be done by the player. <red>Use %player% in the command to act as the players name in console commands")
            helpMessage.add(" • <gold>/pactions info <gray>- While looking at a pressure plate, it will give you infomation about the cast command and when / who set it")
            helpMessage.add(" • <gold>/pactions <gray>- Shows infomation about the avaliable commands")
            helpMessage.add("<gray-=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=--=+=-")

            MessageHelper.sendMessage(sender, helpMessage.joinToString("<newline>"))
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        return when {
            args[0] == "" -> {
                subCommandsList
            }
            args.size == 1 -> {
                subCommandsList.filter { it.contains(args[0], ignoreCase = true) }
            }
            else -> {
                val sc: SubCommandBase = subCommands[args[0]] ?: return emptyList()
                return sc.onTabComplete(sender, command, alias, args)
            }
        }
    }

    fun getSubCommandList(): List<String> {
        return subCommandsList
    }

    fun getSubCommands(): MutableMap<String, SubCommandBase> {
        return subCommands
    }
}