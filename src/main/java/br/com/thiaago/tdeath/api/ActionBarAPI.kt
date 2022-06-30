package br.com.thiaago.tdeath.api

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.entity.Player

class ActionBarAPI {

    companion object {
        fun send(player: Player, message: String) {
            val componentArray: Array<BaseComponent> = TextComponent.fromLegacyText(message)
            val actionBar = ChatMessageType.ACTION_BAR

            componentArray.forEach { player.spigot().sendMessage(actionBar, it) }
        }
    }
}