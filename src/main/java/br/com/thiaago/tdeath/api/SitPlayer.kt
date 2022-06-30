package br.com.thiaago.tdeath.api

import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Spider
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class SitPlayer {

    fun sit(player: Player, pos: Location): Spider {
        val world = player.world
        val spider = world.spawnEntity(pos.add(0.0, -0.7, 0.0), EntityType.SPIDER) as Spider
        spider.setAI(false)
        spider.isInvulnerable = true
        spider.isCollidable = false
        spider.isSilent = true
        spider.setGravity(false)
        spider.addPotionEffect(
            PotionEffect(
                PotionEffectType.INVISIBILITY, 9999999, 1, false, false, false
            )
        )
        spider.addPassenger(player)
        spider.setRotation(player.location.yaw, player.location.pitch)
        return spider
    }

}