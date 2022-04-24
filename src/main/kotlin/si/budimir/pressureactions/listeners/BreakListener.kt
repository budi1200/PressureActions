package si.budimir.pressureactions.listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.utils.LocationUtils
import si.budimir.pressureactions.utils.MessageHelper

class BreakListener(private val plugin: PressureActionsMain): Listener {
    @EventHandler
    fun onBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block

        if (block.type != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return

        val blockLocString = LocationUtils.locToString(block.location)

        val plateData = plugin.mainConfig.configuredPlates[blockLocString] ?: return

        plateData.removeFromConfig(block.location)

        MessageHelper.sendMessage(player, plugin.langConfig.plateDestroyed)
    }
}