package red.jad.clearchatonjoin;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

@Mod(modid = "clearchatonjoin", useMetadata = true, clientSideOnly = true, serverSideOnly = false)
@Mod.EventBusSubscriber
public class ClearChatOnJoin{
	@EventHandler
    public void postInit(FMLPostInitializationEvent e){ MinecraftForge.EVENT_BUS.register(this); }
	public void onLogin(ClientConnectedToServerEvent e) { MinecraftForge.EVENT_BUS.register(new ChatClearer()); }
	class ChatClearer {
		public void onTick(PlayerTickEvent e) {
			Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(false);
			if(e.player.ticksExisted >= 20) MinecraftForge.EVENT_BUS.unregister(this);
		}
	}
}
