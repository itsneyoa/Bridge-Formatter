package neyoa.bridge

import gg.essential.universal.UChat

object Chat {
    fun chat(message: String) = UChat.chat(message)
    fun chat(messages: Array<String>) {
        for (message in messages) { chat(message) }
    }

    fun hydrateTemplateMessage(template: String, username: String, message: String): String {
        return template.replace(Regex("%name", RegexOption.IGNORE_CASE), username).replace(Regex("%message", RegexOption.IGNORE_CASE), message)
    }
}