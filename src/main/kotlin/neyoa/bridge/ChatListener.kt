package neyoa.bridge

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.client.event.ClientChatReceivedEvent
import gg.essential.universal.wrappers.message.UTextComponent

class ChatListener {
    companion object {
        fun isBridgeMessage(message: String) = Regex(
            "^(Guild|Officer) > (?:\\[.*] )?${Bridge.config.username.trim()}",
            RegexOption.IGNORE_CASE
        ).containsMatchIn(message)

        fun getMessageParts(message: String, chat: String): MatchResult.Destructured {
            return Regex("^${chat} > (?:\\[\\w+\\+*])? ?${Bridge.config.username.trim()} ?(?:\\[\\w+])?: (?<nickname>.{1,32}): (?<chatMessage>.+)\$").find(
                message
            )!!.destructured
        }
    }

    @SubscribeEvent(receiveCanceled = true)
    fun onChatMessage(event: ClientChatReceivedEvent) {
        val message = UTextComponent.stripFormatting(event.message.unformattedText)
        if (Bridge.config.firstLaunch) {
            Bridge.chat.chat(
                arrayOf(
                    "&dThank you for installing neyoa's Chat Bridge Formatter!",
                    "&7To get started run &9/chatbridge&7 and checkout the wiki at &bhttps://github.com/itsneyoa/Bridge-Formatter/wiki",
                )
            )
            Bridge.config.firstLaunch = false
        }

        if (Bridge.config.enabled && Bridge.config.username.isNotEmpty()) {
            if (!isBridgeMessage(message)) return
            if (!event.isCancelable) return

            event.isCanceled = true

            when (message.split(" ")[0]) {
                "Guild" -> {
                    val (nickname, chatMessage) = getMessageParts(message, "Guild")
                    Bridge.chat.chat(
                        Bridge.chat.hydrateTemplateMessage(
                            Bridge.config.guildDisplay,
                            nickname,
                            chatMessage
                        )
                    )
                }
                "Officer" -> {
                    val (nickname, chatMessage) = getMessageParts(message, "Officer")
                    Bridge.chat.chat(
                        Bridge.chat.hydrateTemplateMessage(
                            Bridge.config.officerDisplay,
                            nickname,
                            chatMessage
                        )
                    )
                }
            }
        }
    }
}
