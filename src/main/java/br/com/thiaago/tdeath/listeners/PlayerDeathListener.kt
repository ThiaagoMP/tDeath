package br.com.thiaago.tdeath.listeners

import br.com.thiaago.tdeath.api.SitPlayer
import br.com.thiaago.tdeath.constants.Configurations
import br.com.thiaago.tdeath.data.controller.PlayersKnockedController
import br.com.thiaago.tdeath.data.entity.PlayerKnocked
import br.com.thiaago.tdeath.events.PlayerKnockedOutEvent
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerDeathListener(private val playersKnockedController: PlayersKnockedController) : Listener {

    private val maxValue = 99999

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity.type != EntityType.PLAYER) return
        val player = event.entity as Player

        val playerKnocked = playersKnockedController.find(player)

        if (playerKnocked != null) {
            playersKnockedController.remove(player)
            return
        }

        if (player.health - event.finalDamage <= 0) {
            val playerKnockedOutEvent = PlayerKnockedOutEvent(player)
            Bukkit.getPluginManager().callEvent(playerKnockedOutEvent)
            if (playerKnockedOutEvent.isCancelled) return

            event.isCancelled = true
            val livingEntity = SitPlayer().sit(player, player.location)
            playersKnockedController.playersKnocked.add(
                PlayerKnocked(
                    player,
                    (System.currentTimeMillis() + (1000 * Configurations.timeRevive)),
                    livingEntity
                )
            )
            player.health = 0.1
            player.addPotionEffects(
                listOf(
                    PotionEffect(PotionEffectType.BLINDNESS, maxValue, maxValue),
                    PotionEffect(PotionEffectType.SLOW, maxValue, maxValue)
                )
            )
        }
    }

}