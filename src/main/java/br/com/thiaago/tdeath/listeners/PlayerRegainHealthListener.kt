package br.com.thiaago.tdeath.listeners

import br.com.thiaago.tdeath.data.controller.PlayersKnockedController
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityRegainHealthEvent

class PlayerRegainHealthListener(private val playersKnockedController: PlayersKnockedController) : Listener {

    @EventHandler
    fun onRegain(event: EntityRegainHealthEvent) {
        if (event.entity.type != EntityType.PLAYER) return

        playersKnockedController.find(event.entity as Player) ?: return

        event.isCancelled = true
    }

}