package br.com.thiaago.tdeath.constants

import br.com.thiaago.tdeath.TDeath

enum class Messages(val message: String) {

    NOT_PERMISSION(TDeath.instance!!.config.getString("NOT_PERMISSION")!!.replace('&', '§')),
    PLEASE_EMPTY_INVENTORY(TDeath.instance!!.config.getString("PLEASE_EMPTY_INVENTORY")!!.replace('&', '§')),
    ITEM_ADDED_INVENTORY(TDeath.instance!!.config.getString("ITEM_ADDED_INVENTORY")!!.replace('&', '§')),
    YOU_REVIVED_BY_PLAYER(TDeath.instance!!.config.getString("YOU_REVIVED_BY_PLAYER")!!.replace('&', '§')),
    YOU_REVIVED_PLAYER(TDeath.instance!!.config.getString("YOU_REVIVED_PLAYER")!!.replace('&', '§')),
    TIME_SECONDS_DIE(TDeath.instance!!.config.getString("TIME_SECONDS_DIE")!!.replace('&', '§')),
    YOU_DEAD(TDeath.instance!!.config.getString("YOU_DEAD")!!.replace('&', '§'))

}