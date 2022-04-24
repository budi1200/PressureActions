package si.budimir.pressureactions.config

import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.config.data.MainConfigData

class MainConfig(plugin: PressureActionsMain) : ConfigBase<MainConfigData>(plugin, "config.conf", MainConfigData::class.java)