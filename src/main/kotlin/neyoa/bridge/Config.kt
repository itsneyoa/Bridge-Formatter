@file:Suppress("unused")

package neyoa.bridge

import gg.essential.vigilance.Vigilant
import gg.essential.universal.UDesktop
import gg.essential.vigilance.data.*
import java.io.File
import java.net.URI

object Config : Vigilant(File("./config/bridge.toml"), "Chat Bridge", sortingBehavior = ConfigSorting) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Enabled",
        description = "Toggle bridge formatting on and off",
        category = "General"
    )
    var enabled = true

    @Property(
        type = PropertyType.TEXT,
        name = "Username",
        description = "The username of the bridge bot",
        category = "General"
    )
    var username = ""

    @Property(
        type = PropertyType.SWITCH,
        name = "First Launch",
        description = "Is this the first time the mod has been ran?",
        category = "General",
        hidden = true
    )
    var firstLaunch = true


    @Property(
        type = PropertyType.BUTTON, name = "How to format",
        description = "Click to read instructions on how to format chat messages as you want them.",
        category = "Display",
        placeholder = "Open"
    )
    @Suppress("unused")
    fun openDocs() = UDesktop.browse(URI.create("https://github.com/itsneyoa/Bridge-Formatter/wiki"))

    @Property(
        type = PropertyType.TEXT,
        name = "Guild Chat Display",
        description = "How to render guild bridge messages to the chat",
        category = "Display"
    )
    var guildDisplay = "&2Guild > &6%name &5[Bridge]&f: %message"

    @Property(
        type = PropertyType.TEXT,
        name = "Officer Chat Display",
        description = "How to render officer bridge messages to the chat",
        category = "Display"
    )
    var officerDisplay = "&3Officer > &6%name &5[Bridge]&f: %message"

    fun init() {
        initialize()
    }

    private object ConfigSorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> = Comparator { o1, o2 ->
            if (o1.name == "General") return@Comparator -1
            if (o2.name == "General") return@Comparator 1
            else compareValuesBy(o1, o2) {
                it.name
            }
        }
    }
}
