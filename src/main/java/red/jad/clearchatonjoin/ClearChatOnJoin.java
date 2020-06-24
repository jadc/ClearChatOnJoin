package red.jad.clearchatonjoin;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "clearchatonjoin", useMetadata = true, clientSideOnly = true, serverSideOnly = false)

public class ClearChatOnJoin{
	@EventHandler
    public void postInit(FMLPostInitializationEvent e){
 		if(e.getSide() == Side.CLIENT) MinecraftForge.EVENT_BUS.register(this);
 	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onLogin(ClientConnectedToServerEvent e) {
    	MinecraftForge.EVENT_BUS.register(new ChatClearer());
	}
    
	class ChatClearer {
		@SideOnly(Side.CLIENT)
		@SubscribeEvent(priority = EventPriority.LOWEST)
		public void onTick(PlayerTickEvent e) {
			Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages(false);
			if(e.player.ticksExisted >= 20) MinecraftForge.EVENT_BUS.unregister(this);
		}
	}
}
