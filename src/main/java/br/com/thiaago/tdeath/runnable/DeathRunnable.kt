package br.com.thiaago.tdeath.runnable

import br.com.thiaago.tdeath.api.ActionBarAPI
import br.com.thiaago.tdeath.constants.Messages
import br.com.thiaago.tdeath.data.controller.PlayersKnockedController
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import kotlin.math.abs

class DeathRunnable {

    fun getRunnable(playersKnockedController: PlayersKnockedController, plugin: JavaPlugin): BukkitTask {
        return object : BukkitRunnable() {

            override fun run() {
                Bukkit.getOnlinePlayers().forEach { players: Player ->
                    if (playersKnockedController.find(players) != null) {
                        val playerKnocked = playersKnockedController.find(players) ?: return@forEach
                        if (System.currentTimeMillis() >= playerKnocked.time) {
                            players.gameMode = GameMode.SURVIVAL
                            players.damage(players.maxHealth)
                            players.sendMessage(Messages.YOU_DEAD.message)
                        } else {
                            val time = abs(((System.currentTimeMillis() - playerKnocked.time) / 1000)) + 1
                            ActionBarAPI.send(
                                players, Messages.TIME_SECONDS_DIE.message.replace("%time%", time.toString())
                            )
                        }
                    }
                }
            }

        }.runTaskTimer(plugin, 0, 20 * 1)
    }

}