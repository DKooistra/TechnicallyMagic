package net.technicallyMagic.particle.custom;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.technicallyMagic.logic.ParticleIDInfo;
import team.lodestar.lodestone.registry.common.particle.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.builder.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleDataBuilder;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleDataBuilder;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleData;
import team.lodestar.lodestone.systems.particle.data.spin.SpinParticleDataBuilder;

import java.awt.*;

public class ParticleSpawner {


    public static WorldParticleBuilder spawnParticle(Level level, Vec3 pos, GenericParticleDataBuilder scaleData,
                                                     GenericParticleDataBuilder transData, ColorParticleDataBuilder colorData,
                                                     SpinParticleDataBuilder spinData) {

        return WorldParticleBuilder.create(LodestoneParticleRegistry.SPARK_PARTICLE)
                // Set scale over time
                .setScaleData(scaleData.build())
                // Set transparency over time
                .setTransparencyData(transData.build())
                // Set color stuffs over time
                .setColorData(colorData.build())
                // Set spin over time
                .setSpinData(spinData.setSpinOffset((level.getGameTime() * 0.2f) % 6.28f).build())
                // How longs it lasts
                .setLifetime(30)
                // What's its movement lookin like
                .addMotion(0, 0.01f, 0)
                // It hit stuff?
                .enableNoClip()
                // Where we droppin boys?
                .spawn(level, pos.x, pos.y, pos.z);


    }

    public static void spawnParticle(Level level, Vec3 pos, WorldParticleBuilder particleBuilder) {
        particleBuilder.spawn(level, pos.x, pos.y, pos.z);
    }














}
