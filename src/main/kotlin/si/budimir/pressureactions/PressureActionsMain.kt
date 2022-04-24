package si.budimir.pressureactions

import org.bukkit.NamespacedKey
import org.bukkit.plugin.java.JavaPlugin
import si.budimir.pressureactions.commands.PActionsCommand
import si.budimir.pressureactions.config.LangConfig
import si.budimir.pressureactions.config.MainConfig
import si.budimir.pressureactions.config.data.LangConfigData
import si.budimir.pressureactions.config.data.MainConfigData
import si.budimir.pressureactions.listeners.BreakListener
import si.budimir.pressureactions.listeners.InteractListener
import si.budimir.pressureactions.listeners.PlaceListener

class PressureActionsMain: JavaPlugin() {
    // Config variables
    lateinit var mainConfigObj: MainConfig
    lateinit var mainConfig: MainConfigData
    lateinit var langConfigObj: LangConfig
    lateinit var langConfig: LangConfigData

    // Commands
    lateinit var mainCommand: PActionsCommand

    // Keys
    val actionPlateKey = NamespacedKey(this, "isActionPlate")

    companion object {
        lateinit var instance: PressureActionsMain
    }

    override fun onEnable() {
        instance = this

        // Load configs
        mainConfigObj = MainConfig(this)
        mainConfig = mainConfigObj.getConfig()

        langConfigObj = LangConfig(this)
        langConfig = langConfigObj.getConfig()

        // Load commands
        mainCommand = PActionsCommand(instance)
        getCommand("pactions")?.setExecutor(mainCommand)

        // Register event handlers
        server.pluginManager.registerEvents(PlaceListener(this), this)
        server.pluginManager.registerEvents(BreakListener(this), this)
        server.pluginManager.registerEvents(InteractListener(this), this)
    }
}