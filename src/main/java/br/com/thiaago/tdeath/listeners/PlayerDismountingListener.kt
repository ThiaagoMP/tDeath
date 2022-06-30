package br.com.thiaago.tdeath.listeners

import br.com.thiaago.tdeath.data.controller.PlayersKnockedController
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.spigotmc.event.entity.EntityDismountEvent

class PlayerDismountingListener(private val playersKnockedController: PlayersKnockedController) : Listener {

    @EventHandler
    fun onDismounting(event: EntityDismountEvent) {
        if (event.entity.type != EntityType.PLAYER) return
        val player = event.entity as Player

        if (playersKnockedController.find(player) != null)
            event.isCancelled = true
    }

}