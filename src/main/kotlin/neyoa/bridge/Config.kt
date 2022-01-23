package neyoa.bridge

import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.*
import java.io.File

object Config : Vigilant(File("./config/bridge.toml"), "Chat Bridge", sortingBehavior = ConfigSorting) {
    @Property(
        type = PropertyType.SWITCH,
        name = "Enabled",
        description = "Toggle bridge formatting on and off",
        category = "General"
    )
    var enabled = Defaults.General.enabled

    @Property(
        type = PropertyType.TEXT,
        name = "Username",
        description = "The username of the bridge bot",
        category = "General", placeholder = "Username"
    )
    var username = Defaults.General.username

    @Property(
        type = PropertyType.SWITCH,
        name = "First Launch",
        description = "Is this the first time the mod has been ran?",
        category = "General",
        hidden = true
    )
    var firstLaunch = Defaults.General.firstLaunch

    @Property(
        type = PropertyType.TEXT,
        name = "Guild Chat Display",
        description = "How to render guild bridge messages to the chat",
        category = "Display"
    )
    var guildDisplay = Defaults.Display.guild

    @Property(
        type = PropertyType.TEXT,
        name = "Officer Chat Display",
        description = "How to render officer bridge messages to the chat",
        category = "Display"
    )
    var officerDisplay = Defaults.Display.officer

    @Property(
        type = PropertyType.BUTTON,
        name = "Configuration help",
        description = "Display all the configuration options in chat. Alternative to /cb options",
        placeholder = "Show options",
        category = "Display"
    )
    @Suppress("UNUSED")
    fun onDisplayColours() = Bridge.chat.showConfigOptions()

    // -----------------------------------------------------------------------------------------------------------------

    @Property(
        type = PropertyType.BUTTON, name = "Reset Guild Chat renderer",
        description = "Reset the guild chat renderer to its default value",
        placeholder = "Reset",
        category = "Danger zone"
    )
    fun onGuildRendererReset() = run { guildDisplay = Defaults.Display.guild }

    @Property(
        type = PropertyType.BUTTON, name = "Reset Officer Chat renderer",
        description = "Reset the officer chat renderer to its default value",
        placeholder = "Reset",
        category = "Danger zone"
    )
    fun onOfficerRendererReset() = run { officerDisplay = Defaults.Display.officer }

    @Property(
        type = PropertyType.BUTTON, name = "Reset all chat renderers",
        description = "Reset guild and officer chat renderers to their default values",
        placeholder = "Reset",
        category = "Danger zone"
    )
    @Suppress("UNUSED")
    fun onAllRenderersReset() = run { onGuildRendererReset(); onOfficerRendererReset(); }

    // -----------------------------------------------------------------------------------------------------------------

    fun init() = initialize()
    fun open() = run { Bridge.gui = gui() }

    private object ConfigSorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> = Comparator { o1, o2 ->
            if (o1.name == "General") return@Comparator -1
            if (o2.name == "General") return@Comparator 1
            if (o1.name == "Display") return@Comparator -1
            if (o2.name == "Display") return@Comparator 1
            else compareValuesBy(o1, o2) {
                it.name
            }
        }
    }

    private object Defaults {
        object General {
            const val enabled = true
            const val firstLaunch = true
            const val username = ""
        }

        object Display {
            const val guild = "&2Guild > &6%name &5[Bridge]&f: %message"
            const val officer = "&3Officer > &6%name &5[Bridge]&f: %message"
        }
    }
}
