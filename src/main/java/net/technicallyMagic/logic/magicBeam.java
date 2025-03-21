package net.technicallyMagic.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.technicallyMagic.event.ScuffedCustomEventHandler;
import net.technicallyMagic.particle.custom.ParticleConstructor;
import net.technicallyMagic.particle.custom.TestParticles;

import java.util.List;
import java.util.Random;

public class magicBeam {



    public static void fireMagicBeam(dirVec3 fireDirection, Level level, ServerLevel sLevel) {
        double distance = 1.5;
        double radius = 2.75;

        Vec3 coordinate = fireDirection.calcCoord(distance);
        while (checkValidBlock(coordinate, level) && distance <= 20) {
            blockDeletionAlgo(fireDirection, level, sLevel, distance, radius);
            summonParticleProcedure(fireDirection, distance);
            checkAndHurtEntities(fireDirection, level, sLevel, distance, radius);
            distance += 1.0;
            coordinate = fireDirection.calcCoord(distance);
        }

        ScuffedCustomEventHandler.pushParticleTick();

    }

    // Algo takes radius of the beam and destroys all blocks within that radius for each point along the beam
    // Isn't an algo it is legit just brute force should make better but idk
    private static void blockDeletionAlgo(dirVec3 fireDirection, Level level, ServerLevel sLevel, double distance, double radius) {
        double currRadius = 0;
        Random rand = new Random();
        while (currRadius < radius) {
            deleteBlockIfValid(fireDirection.calcCoord(distance, currRadius + rand.nextDouble(0.25), 0, -currRadius + rand.nextDouble(0.25)), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, 0, currRadius + rand.nextDouble(0.25), 0), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, 0, 0, currRadius + rand.nextDouble(0.25)), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, -currRadius + rand.nextDouble(0.25), 0, 0), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, 0, -currRadius + rand.nextDouble(0.25), 0), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, 0, 0, -currRadius + rand.nextDouble(0.25)), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, currRadius + rand.nextDouble(0.25), 0, currRadius + rand.nextDouble(0.25)), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, currRadius + rand.nextDouble(0.25), currRadius + rand.nextDouble(0.25), 0), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, 0, currRadius + rand.nextDouble(0.25), currRadius + rand.nextDouble(0.25)), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, -currRadius + rand.nextDouble(0.25), 0, -currRadius + rand.nextDouble(0.25)), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, currRadius + rand.nextDouble(0.25), -currRadius + rand.nextDouble(0.25), 0), sLevel, level);
            deleteBlockIfValid(fireDirection.calcCoord(distance, 0 + rand.nextDouble(0.25), currRadius + rand.nextDouble(0.25), -currRadius + rand.nextDouble(0.25)), sLevel, level);
            currRadius+=0.26;
        }
    }


    // Level that entity gets taken from needs to be a server level
    // sLevel.getRandomPlayer() will need to be replaced with the player that is shooting (maybe i dont actually know for certain, but probably yes)
    // Does not detect players with .getEntities i think, gotta figure out how to do that
    private static void checkAndHurtEntities(dirVec3 fireDirection, Level level, ServerLevel sLevel, double distance, double radius) {


        Vec3 finl = fireDirection.calcCoord(distance + radius);
        Vec3 init = fireDirection.calcCoord(distance);
        AABB area = new AABB(init, finl);
        List<Entity> entities = sLevel.getEntities(sLevel.getRandomPlayer(), area);

        for (Entity entity : entities) {
            entity.hurt(new DamageSource((level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(DamageTypes.MAGIC))), 15);
        }


    }

    public static void deleteBlock(Vec3 coordinate, ServerLevel sLevel) {
        sLevel.setBlock(getBlockPos(coordinate), Blocks.AIR.defaultBlockState(), 3);
    }

    private static void deleteBlockIfValid(Vec3 coordinate, ServerLevel sLevel, Level level) {
        if (checkValidBlock(coordinate, level)) {
            deleteBlock(coordinate, sLevel);
        }
    }

    private static boolean checkValidBlock(Vec3 coordinate, Level level) {
        BlockPos blockPos = getBlockPos(coordinate);
        float blockExplosionRes = level.getBlockState(blockPos).getBlock().getExplosionResistance();
        return (blockExplosionRes < 31);
    }

    private static BlockPos getBlockPos(Vec3 coordinate) {
        return BlockPos.containing(coordinate.x, coordinate.y, coordinate.z);
    }

    private static void summonParticleProcedure(dirVec3 fireDirection, double distance) {
        Vec3 pos1 = fireDirection.calcCoord(distance);
        Vec3 pos2 = fireDirection.calcCoord(distance + .5);
        ParticleConstructor particle1 = new ParticleConstructor(pos1, "redToPurple");
        ParticleConstructor particle2 = new ParticleConstructor(pos2, "redToPurple");
        ScuffedCustomEventHandler.addParticleToTick(particle1);
        ScuffedCustomEventHandler.addParticleToTick(particle2);
    }

}
