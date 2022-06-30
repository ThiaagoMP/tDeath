package br.com.thiaago.tdeath.listeners

import br.com.thiaago.tdeath.api.ActionBarAPI
import br.com.thiaago.tdeath.constants.Messages
import br.com.thiaago.tdeath.constants.NBTTag
import br.com.thiaago.tdeath.data.controller.PlayersKnockedController
import br.com.thiaago.tdeath.events.PlayerReviveEvent
import io.github.bananapuncher714.nbteditor.NBTEditor
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEntityEvent

class PlayerInteractAtEntityListener(private val playersKnockedController: PlayersKnockedController) : Listener {

    @EventHandler
    fun onInteract(event: PlayerInteractEntityEvent) {
        if (event.rightClicked.type != EntityType.PLAYER) return
        val player = event.player
        if (!NBTEditor.contains(player.inventory.itemInMainHand, NBTTag.REVIVE.tag)) return

        val playerClicked = event.rightClicked as Player
        playersKnockedController.find(playerClicked) ?: return

        val playerReviveEvent = PlayerReviveEvent(playerClicked, player)
        Bukkit.getPluginManager().callEvent(playerReviveEvent)
        if (playerReviveEvent.isCancelled) return

        playersKnockedController.remove(playerClicked)

        playerClicked.health = 2.0
        ActionBarAPI.send(playerClicked, Messages.YOU_REVIVED_BY_PLAYER.message.replace("%player%", player.name))
        playerClicked.playSound(playerClicked, Sound.ENTITY_PLAYER_HURT, 1.0f, 1.0f)

        player.inventory.remove(player.inventory.itemInMainHand)
        player.sendMessage(Messages.YOU_REVIVED_PLAYER.message.replace("%player%", playerClicked.name))
        player.playSound(playerClicked, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f)

    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.damager.type != EntityType.PLAYER) return
        if (NBTEditor.contains((event.damager as Player).inventory.itemInMainHand, NBTTag.REVIVE.tag))
            event.isCancelled = true
    }

}