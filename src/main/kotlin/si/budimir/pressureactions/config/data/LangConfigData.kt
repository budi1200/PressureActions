package si.budimir.pressureactions.config.data

import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class LangConfigData(
    val noPermission: String = "<red>Missing permission <perm>!",
    val plateGiven: String = "<green>You have given yourself a pressure plate with the bound command: <command>",
    val plateNotFound: String = "<red>No attached commands found for target pressure plate.",
    val platePlaced: String = "<green>You have placed a pressure command plate!",
    val plateDestroyed: String = "<red>You have broken a pressure command plate!"
)