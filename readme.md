## Pressure Actions

<img src="https://slocraft.eu/slocraft-logo-512.png" width=124 height=124 align="right"/>

PressureActions was developed for use on the [SloCraft](https://slocraft.eu) network.

Please keep in mind that the plugin has not been updated since May 2022.

### Description

PressureActions allows players to execute commands by stepping on pressure plates. It provides three commands - `give`, `info`, and `reload` - and listens for player interactions with pressure plates to execute the commands.

### Dependencies
PressureActions requires a PaperMC server version 1.18.2 or higher (not tested).

### Configuration
On startup two configuration files are loaded: `config.conf` and `messages.conf`. These files are automatically generated on the first startup and can be found in the plugin's data folder.

- `config.conf` contains settings for the plugin and is used to store the pressure plate data.

- `messages.conf` contains the messages that the plugin uses. These messages can be customized to fit your language and style.

### Usage
  - `/pactions give <true|false> <command>` - Gives you a pressure plate that executes the provided command. If true is specified, the command will be executed by the console, otherwise it will be executed by the player. To substitute the player's name in the command, use `%player%`.
  - `/pactions info` - Provides information about the pressure plate you are looking at.
  - `/pactions reload` - Reloads the plugin's configuration files.

### Permissions
- `pressureactions.use` - Allows the player to use the plugin help command.
- `pressureactions.create` - Allows the player to create new pressure plates.
- `pressureactions.info` - Allows the player to view information about a pressure plate.
- `pressureactions.commands.reload` - Allows the player to reload the plugin configuration.
