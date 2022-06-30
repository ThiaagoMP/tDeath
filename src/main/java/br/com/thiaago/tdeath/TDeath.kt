package br.com.thiaago.tdeath

import br.com.thiaago.tdeath.commands.GiveItemReviveCommand
import br.com.thiaago.tdeath.constants.Configurations
import br.com.thiaago.tdeath.data.controller.PlayersKnockedController
import br.com.thiaago.tdeath.listeners.PlayerDeathListener
import br.com.thiaago.tdeath.listeners.PlayerDismountingListener
import br.com.thiaago.tdeath.listeners.PlayerInteractAtEntityListener
import br.com.thiaago.tdeath.listeners.PlayerRegainHealthListener
import br.com.thiaago.tdeath.runnable.DeathRunnable
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class TDeath : JavaPlugin() {

    val playersKnockedController = PlayersKnockedController(ArrayList())

    private lateinit var runTaskTimer: BukkitTask

    companion object {
        var instance: TDeath? = null
            private set;
    }

    override fun onEnable() {
        saveDefaultConfig()
        registerEvents()
        Configurations.load(this.config)

        getCommand("revive")?.setExecutor(GiveItemReviveCommand())

        instance = this

        runTaskTimer = DeathRunnable().getRunnable(playersKnockedController, this)

    }

    override fun onDisable() {
        playersKnockedController.playersKnocked.forEach { playerKnocked ->
            run {
                playerKnocked.livingEntity.eject()
                playerKnocked.livingEntity.remove()
            }
        }
    }

    private fun registerEvents() {
        Bukkit.getPluginManager().registerEvents(PlayerDeathListener(playersKnockedController), this)
        Bukkit.getPluginManager().registerEvents(PlayerDismountingListener(playersKnockedController), this)
        Bukkit.getPluginManager().registerEvents(PlayerInteractAtEntityListener(playersKnockedController), this)
        Bukkit.getPluginManager().registerEvents(PlayerRegainHealthListener(playersKnockedController), this)
    }
}