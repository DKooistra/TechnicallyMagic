package net.technicallyMagic.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.block.ModBlocks;
import net.technicallyMagic.particle.custom.ParticleConstructor;
import net.technicallyMagic.particle.custom.TestParticles;
import net.technicallyMagic.util.ModTags;

import java.util.ArrayList;


public class ModEvents {

    @Mod.EventBusSubscriber(modid = TechnicallyMagic.MOD_ID)
    public static class modEvents {

        @SubscribeEvent
        public static void onServerTick(TickEvent.ServerTickEvent event) {

            if (!ScuffedCustomEventHandler.particleQueue.isEmpty()) {
                ArrayList<ParticleConstructor> particles = ScuffedCustomEventHandler.particleQueue.remove();
                for (ParticleConstructor particle : particles) {
                    TestParticles.sendTestParticlePacket(particle.position);
                    particle = null;
                }
                particles = null;
            }

        }

        @SubscribeEvent
        public static void onLevelTick(TickEvent.LevelTickEvent event) {

            if (ScuffedCustomEventHandler.tickTracking) {
                ScuffedCustomEventHandler.bumpTick();
            }
        }



        @SubscribeEvent
        public static void onEntityLightningStrike(EntityStruckByLightningEvent event) {

            int powerRemaining = 3;
            int currentDepth = 0;

            LightningBolt lightningBolt = event.getLightning();
            Entity entity = event.getEntity();

            Vec3 strikePos = lightningBolt.position();
            BlockPos blockStruck = BlockPos.containing(strikePos.x, strikePos.y - 1.0E-6D, strikePos.z);

            MinecraftServer minecraftServer = ServerLifecycleHooks.getCurrentServer();
            ServerLevel level = minecraftServer.getLevel(Level.OVERWORLD);
            if (level == null) {
                return;
            }

            BlockState blockState = level.getBlockState(blockStruck);


            // While the lightning is striking/effecting a valid block & has not run through its "power" (powerRemaining)
            while ((blockState.is(Blocks.LIGHTNING_ROD) || blockState.is(Blocks.COPPER_BLOCK) || blockState.is(Blocks.WAXED_COPPER_BLOCK) ||
                    blockState.is(Blocks.EXPOSED_COPPER)) && powerRemaining >= 0)
            {
                currentDepth++;
                // If it is going to be replaced by Elder Copper
                if (!blockState.is(Blocks.LIGHTNING_ROD)) {
                    // Exposed copper uses more "power" to convert,
                    // other forms of copper take too much power and are not converted at all
                    if (blockState.is(Blocks.EXPOSED_COPPER)) {
                        powerRemaining -= 2;
                    } else {
                        powerRemaining--;
                    }

                    level.setBlockAndUpdate(blockStruck, ModBlocks.ELDER_COPPER_BLOCK.get().defaultBlockState());

                }

                blockStruck = BlockPos.containing(strikePos.x, strikePos.y - 1.0E-6D - currentDepth, strikePos.z);
                blockState = level.getBlockState(blockStruck);
            }


        }
    }

}
