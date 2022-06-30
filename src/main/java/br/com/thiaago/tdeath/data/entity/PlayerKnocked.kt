package br.com.thiaago.tdeath.data.entity

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

data class PlayerKnocked(val player: Player, val time: Long, val livingEntity: LivingEntity)