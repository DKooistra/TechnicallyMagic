package net.technicallyMagic.particle.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.technicallyMagic.network.PacketHandler;
import net.technicallyMagic.network.packets.S2AParticleSpawnPacket;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;

import java.awt.*;
import java.util.Random;

public class TestParticles {


    public static void summonTestParticle(Level level, double x, double y, double z) {
        Color startingColor = new Color(141, 5, 5);
        Color endingColor = new Color(74, 0, 131);

        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE)
                .setScaleData(GenericParticleData.create(0.8f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.9f, 0.65f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(30)
                .addMotion(0, 0.01f, 0)
                .enableNoClip()
                .spawn(level, x,y,z);
    }

    public static void sendTestParticlePacket(Vec3 position) {

        PacketHandler.sendToAllPlayers(new S2AParticleSpawnPacket(position, 0));
    }


    public static WorldParticleBuilder createParticle(Color startColor, Color endColor) {

        Random rand = new Random();

        return WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE)
                .setScaleData(GenericParticleData.create(0.8f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.9f, 0.65f).build())
                .setColorData(ColorParticleData.create(startColor, endColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((float)(rand.nextFloat()*6.0)).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(30)
                .addMotion(0, 0.01f, 0)
                .enableNoClip();
    }

    public static void particleSpawner(Level level, Vec3 position, int startColor, int endColor) {
        Color startingColor = new Color(startColor);
        Color endingColor = new Color(endColor);

        WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE)
                .setScaleData(GenericParticleData.create(0.8f, 0).build())
                .setTransparencyData(GenericParticleData.create(0.9f, 0.65f).build())
                .setColorData(ColorParticleData.create(startingColor, endingColor).setCoefficient(1.4f).setEasing(Easing.BOUNCE_IN_OUT).build())
                .setSpinData(SpinParticleData.create(0.2f, 0.4f).setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).setEasing(Easing.QUARTIC_IN).build())
                .setLifetime(30)
                .addMotion(0, 0.01f, 0)
                .enableNoClip()
                .spawn(level, position.x, position.y, position.z);
    }
}
