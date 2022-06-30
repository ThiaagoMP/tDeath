package br.com.thiaago.tdeath.commands

import br.com.thiaago.tdeath.TDeath
import br.com.thiaago.tdeath.api.ActionBarAPI
import br.com.thiaago.tdeath.api.ItemBuilder
import br.com.thiaago.tdeath.constants.Messages
import br.com.thiaago.tdeath.constants.NBTTag
import io.github.bananapuncher714.nbteditor.NBTEditor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GiveItemReviveCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("§cCommand intended for players only!")
            return true
        }
        if (!sender.hasPermission("revive.use")) {
            sender.sendMessage(Messages.NOT_PERMISSION.message)
            return true
        }
        if (sender.inventory.firstEmpty() == -1) {
            sender.sendMessage(Messages.PLEASE_EMPTY_INVENTORY.message)
            return true
        }

        val section = TDeath.instance!!.config.getConfigurationSection("ITEM")

        val item = NBTEditor.set(
            ItemBuilder(Material.getMaterial(section?.getString("type")!!)!!).setDisplayName(section.getString("displayName")!!)
                .setLore(section.getStringList("lore")).build(),
            sender.name,
            NBTTag.REVIVE.tag
        )
        sender.inventory.addItem(item)
        ActionBarAPI.send(sender, Messages.ITEM_ADDED_INVENTORY.message)
        return false
    }


}