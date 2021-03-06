package net.tds.magicpets;

import java.util.Arrays;

import net.minecraftforge.common.MinecraftForge;
import net.tds.magicpets.event.EntityConstructionEvent;
import net.tds.magicpets.handler.ConnectionHandler;
import net.tds.magicpets.item.Items;
import net.tds.magicpets.lib.Config;
import net.tds.magicpets.lib.Reference;
import net.tds.magicpets.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

//99% of these commits are for dev discussion and notification.

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class ModJam {
	
	@SidedProxy(serverSide = Reference.PROXY_SERVER, clientSide = Reference.PROXY_CLIENT)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_ID)
	public static ModJam instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		getModInfo(event.getModMetadata());
		new Config(event.getSuggestedConfigurationFile());
		new Items();
		MinecraftForge.EVENT_BUS.register(new EntityConstructionEvent());
	}
	
	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		
		NetworkRegistry.instance().registerConnectionHandler(new ConnectionHandler());
	}
	
	public void getModInfo(ModMetadata meta) {
		
		meta.authorList = Arrays.asList("ThisGuy1045, Darkhax, ShadowChild, HoopAWolf");
		meta.logoFile = "/assets/modjam3/logo.png";
		meta.autogenerated = false;
		meta.description = "I can't tell you just yet ;p";
		meta.url = "www.darkhax.net/modjam.html";
	}
}
