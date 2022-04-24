package si.budimir.pressureactions.listeners

import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.utils.LocationUtils

class InteractListener(private val plugin: PressureActionsMain): Listener {
    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val player = event.player

        if (event.action != Action.PHYSICAL) return

        if (event.clickedBlock?.type != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return

        val blockLocation = event.clickedBlock!!.location
        val blockLocationString = LocationUtils.locToString(blockLocation)
        val plateData = plugin.mainConfig.configuredPlates[blockLocationString] ?: return

        val parsedCommand = plateData.command.replace("%player%", player.name)
        var commandSender: CommandSender = player

        if (plateData.useConsole) {
            commandSender = plugin.server.consoleSender
        }

        plugin.server.dispatchCommand(commandSender, parsedCommand)
    }
}