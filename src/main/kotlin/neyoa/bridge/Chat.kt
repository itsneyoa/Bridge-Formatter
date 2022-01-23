package neyoa.bridge

import gg.essential.universal.UChat

object Chat {
    fun chat(message: String) = UChat.chat(message)
    fun chat(messages: Array<String>) {
        for (message in messages) {
            chat(message)
        }
    }

    fun hydrateTemplateMessage(template: String, username: String, message: String): String {
        return template.replace(Regex("%name", RegexOption.IGNORE_CASE), username)
            .replace(Regex("%message", RegexOption.IGNORE_CASE), message)
    }

    fun showConfigOptions() {
        val zeroWidth = "ࠀ"
        fun escapeCode(code: String) = "&$zeroWidth$code"
        fun showCode(code: String) = "&r&${code}${escapeCode(code)}"

        chat(
            arrayOf(
                "",
                "&b&l----- Colours and Styles -----&r",
                "These can be chained together: ${escapeCode("b")} + ${escapeCode("k")} + ${escapeCode("n")} = &b&l&nneyoa",
                "",
                "${showCode("0")} ${showCode("1")} ${showCode("2")} ${showCode("3")}",
                "${showCode("4")} ${showCode("5")} ${showCode("6")} ${showCode("7")}",
                "${showCode("8")} ${showCode("9")} ${showCode("a")} ${showCode("b")}",
                "${showCode("c")} ${showCode("d")} ${showCode("e")} ${showCode("f")}",
                "",
                "${escapeCode("k")} &kneyoa&r ${showCode("l")} neyoa",
                "${showCode("m")} neyoa&r ${showCode("n")} neyoa",
                "${showCode("o")} neyoa&r ${showCode("r")} (Reset)",
                "",
                "&b&l----- Text Replacement -----&r",
                "&9%name&r will be replaced with the name of the person who sent the message",
                "&9%message&r will be replaced with the message content",
                ""
            )
        )
    }
}