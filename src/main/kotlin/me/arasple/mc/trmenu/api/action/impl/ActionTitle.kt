package me.arasple.mc.trmenu.api.action.impl

import io.izzel.taboolib.module.locale.TLocale
import io.izzel.taboolib.util.Strings
import me.arasple.mc.trmenu.api.action.base.AbstractAction
import me.arasple.mc.trmenu.api.action.base.ActionOption
import me.arasple.mc.trmenu.util.Regexs
import org.bukkit.entity.Player

/**
 * @author Arasple
 * @date 2021/1/29 18:01
 * ` `
 */
class ActionTitle(
    val title: String,
    val subTitle: String,
    val fadeIn: Int = 15,
    val stay: Int = 20,
    val fadeOut: Int = 15,
    option: ActionOption
) : AbstractAction(option = option) {

    override fun onExecute(player: Player, placeholderPlayer: Player) {
        TLocale.Display.sendTitle(
            player,
            parse(placeholderPlayer, title),
            parse(placeholderPlayer, subTitle),
            fadeIn,
            stay,
            fadeOut
        )
    }

    companion object {

        private val name = "(send)?-?(sub)?titles?".toRegex()

        private val parser: (Any, ActionOption) -> AbstractAction = { value, option ->
            var content: String = value.toString()
            val replacements = Regexs.SENTENCE.findAll(content).mapIndexed { index, result ->
                content = content.replace(result.value, "{$index}")
                index to result.groupValues[1]
            }.toMap().values.toTypedArray()

            val split = content.split(" ", limit = 5)

            ActionTitle(
                Strings.replaceWithOrder(split.getOrElse(0) { "" }, *replacements),
                Strings.replaceWithOrder(split.getOrElse(1) { "" }, *replacements),
                split.getOrNull(2)?.toIntOrNull() ?: 15,
                split.getOrNull(3)?.toIntOrNull() ?: 20,
                split.getOrNull(4)?.toIntOrNull() ?: 15,
                option
            )
        }

        val registery = name to parser

    }

}