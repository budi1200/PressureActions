package si.budimir.pressureactions.config.data

import org.bukkit.Location
import org.spongepowered.configurate.objectmapping.ConfigSerializable
import org.spongepowered.configurate.objectmapping.meta.Comment
import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.utils.LocationUtils

@ConfigSerializable
data class MainConfigData(
    @Comment("Optional comment")
    val pluginPrefix: String = "<gold><bold>PressureActions <white>» <reset>",
    val configuredPlates: HashMap<String, PlateData> = hashMapOf()
)

@ConfigSerializable
data class PlateData(
    val command: String,
    val itemOwner: String,
    val placedTime: Long,
    var placedBy: String,
    val useConsole: Boolean
) {
    fun saveToConfig(location: Location) {
        val locString = LocationUtils.locToString(location)

        val confData = PressureActionsMain.instance.mainConfig
        confData.configuredPlates[locString] = this

        PressureActionsMain.instance.mainConfigObj.saveConfig(confData)
    }

    fun removeFromConfig(location: Location) {
        val locString = LocationUtils.locToString(location)

        val confData = PressureActionsMain.instance.mainConfig
        confData.configuredPlates.remove(locString)

        PressureActionsMain.instance.mainConfigObj.saveConfig(confData)
    }
}