package neyoa.bridge

import gg.essential.vigilance.Vigilance
import gg.essential.universal.UScreen

import net.minecraft.client.Minecraft

import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent

@Mod(
    modid = Bridge.MODID,
    version = Bridge.VERSION,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true
)
class Bridge {
    companion object {
        const val MODID = "bridgeformatter"
        const val VERSION = "1.0.0"
        val mc: Minecraft = Minecraft.getMinecraft()
        val config = Config
        var gui: UScreen? = null
        val chat = Chat
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        Vigilance.initialize()
        config.init()

        MinecraftForge.EVENT_BUS.register(this)
        ClientCommandHandler.instance.registerCommand(Command())
        MinecraftForge.EVENT_BUS.register(ChatListener())
    }

    @SubscribeEvent
    fun tick(event: TickEvent.ClientTickEvent) = tick()

    private fun tick() {
        if (gui != null) {
            try {
                mc.displayGuiScreen(gui)
            } catch (error: Exception) {
                error.printStackTrace()
            }
            gui = null
        }
    }
}