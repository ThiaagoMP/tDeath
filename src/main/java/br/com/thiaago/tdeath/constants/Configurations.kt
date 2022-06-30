package br.com.thiaago.tdeath.constants

import org.bukkit.configuration.file.FileConfiguration

class Configurations {

    companion object {

        var timeRevive = 60

        fun load(config: FileConfiguration) {
            timeRevive = config.getInt("TIME_TO_REVIVED_SECONDS")
        }

    }

}