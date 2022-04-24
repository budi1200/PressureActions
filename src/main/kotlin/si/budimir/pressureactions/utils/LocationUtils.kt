package si.budimir.pressureactions.utils

import org.bukkit.Bukkit
import org.bukkit.Location

object LocationUtils {
    fun locToString(location: Location): String {
        return location.world.name + "/" + location.blockX + "/" + location.blockY + "/" + location.blockZ
    }

    fun stringToLoc(locString: String): Location {
        val split: Array<String> = locString.split("/").toTypedArray()

        return Location(
            Bukkit.getWorld(split[0]),
            split[1].toInt().toDouble(), split[2].toInt().toDouble(), split[3].toInt().toDouble()
        )
    }
}