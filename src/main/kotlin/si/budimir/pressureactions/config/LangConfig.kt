package si.budimir.pressureactions.config

import si.budimir.pressureactions.PressureActionsMain
import si.budimir.pressureactions.config.data.LangConfigData

class LangConfig(plugin: PressureActionsMain) : ConfigBase<LangConfigData>(plugin, "messages.conf", LangConfigData::class.java)