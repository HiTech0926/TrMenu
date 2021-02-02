package me.arasple.mc.trmenu.api.menu

import me.arasple.mc.trmenu.api.receptacle.window.Receptacle
import org.bukkit.entity.Player

/**
 * @author Arasple
 * @date 2020/12/4 22:30
 */
interface IManager {

    fun getViewingReceptacle(player: Player): Receptacle?

    fun setViewingReceptacle(player: Player, receptacle: Receptacle? = null)

}