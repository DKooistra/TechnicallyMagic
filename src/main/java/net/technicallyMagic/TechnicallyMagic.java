package net.technicallyMagic;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.technicallyMagic.block.ModBlocks;
import net.technicallyMagic.entity.ModEntities;
import net.technicallyMagic.event.ClientModEvents;
import net.technicallyMagic.item.ModCreativeModTabs;
import net.technicallyMagic.item.ModItems;
import net.technicallyMagic.logic.ParticleIDInfo;
import net.technicallyMagic.network.PacketHandler;
import net.technicallyMagic.sound.ModSounds;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TechnicallyMagic.MOD_ID)
public class TechnicallyMagic
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "technicallymagic";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public TechnicallyMagic()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Added Lines
        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);


        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Add ModEvents listener
        //MinecraftForge.EVENT_BUS.addListener(animEvents::onPlayerTick);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        GeckoLib.initialize();

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);


        // Register setup events
        modEventBus.addListener(ClientModEvents::onClientSetup);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        // Build the particleIDInfo library for fun!
        ParticleIDInfo.buildIDInfoLibrary();

    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(PacketHandler::register);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }


}
