package net.technicallyMagic.particle.custom;

import net.minecraft.world.phys.Vec3;
import net.technicallyMagic.logic.ParticleIDInfo;

public class ParticleConstructor {

    public Vec3 position;
    public byte particleIDIndex;

    public ParticleConstructor(Vec3 position, String particleID) {
        this.position = position;
        this.particleIDIndex = ParticleIDInfo.getIndex(particleID);
    }

    public ParticleConstructor(Vec3 position, byte particleIDIndex) {
        this.position = position;
        this.particleIDIndex = particleIDIndex;
    }

}
