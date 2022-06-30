package br.com.thiaago.tdeath.data.controller

import br.com.thiaago.tdeath.data.entity.PlayerKnocked
import org.bukkit.entity.Player

class PlayersKnockedController(val playersKnocked: MutableList<PlayerKnocked>) {

    fun find(player: Player): PlayerKnocked? {
        return playersKnocked.firstOrNull { playerKnocked -> playerKnocked.player.name == player.name }
    }

    fun remove(player: Player) {
        val playerKnocked = find(player) ?: return
        val livingEntity = playerKnocked.livingEntity
        livingEntity.eject()
        livingEntity.remove()
        player.teleport(player.location.add(0.0, 0.7, 0.0))
        player.activePotionEffects.forEach { potionEffect -> player.removePotionEffect(potionEffect.type) }
        playersKnocked.remove(playerKnocked)
    }
}