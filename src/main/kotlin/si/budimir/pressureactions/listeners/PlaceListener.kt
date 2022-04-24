package si.budimir.pressureactions.listeners

import com.google.gson.Gson
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.persistence.PersistentDataType
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.config.data.PlateData
import si.budimir.pressureactions.utils.MessageHelper

class PlaceListener(private val plugin: PressureActionsMain): Listener {

    @EventHandler
    fun onPlace(event: BlockPlaceEvent) {
        val player = event.player

        if (event.hand != EquipmentSlot.HAND) return

        val item = event.itemInHand

        if (item.type != Material.HEAVY_WEIGHTED_PRESSURE_PLATE) return

        val itemMeta = item.itemMeta
        // Check if item contains actionPlate key
        if (!itemMeta.persistentDataContainer.has(plugin.actionPlateKey)) return

        val plateDataString = itemMeta.persistentDataContainer[plugin.actionPlateKey, PersistentDataType.STRING]!!
        val plateData = Gson().fromJson(plateDataString, PlateData::class.java)

        plateData.placedBy = player.name
        plateData.saveToConfig(event.block.location)

        MessageHelper.sendMessage(player, plugin.langConfig.platePlaced)
    }
}