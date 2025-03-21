package net.technicallyMagic.network.packets;


import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import net.technicallyMagic.logic.ParticleIDInfo;
import net.technicallyMagic.particle.custom.ParticleSpawner;
import net.technicallyMagic.particle.custom.TestParticles;
import team.lodestar.lodestone.systems.particle.data.color.ColorParticleDataBuilder;

import java.util.function.Supplier;

/*
    This class is for implementing Lodestone particles for all players in a world
    Because Lodestone particles must be spawned in client-side (client level object), a packet
    must be sent out to all clients in order to send the correct particle.
 */
public class S2AParticleSpawnPacket {

    private final Vec3 position;
    private final int particleIDIndex;

    public S2AParticleSpawnPacket(Vec3 position, int particleIDIndex) {
        this.position = position;
        this.particleIDIndex = particleIDIndex;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeFloat((float)position.x);
        buf.writeFloat((float)position.y);
        buf.writeFloat((float)position.z);
        buf.writeByte(particleIDIndex);
    }

    public S2AParticleSpawnPacket(FriendlyByteBuf buf) {
        this.position = new Vec3(buf.readFloat(), buf.readFloat(), buf.readFloat());
        this.particleIDIndex = buf.readByte();
    }

    public void handle(Supplier<NetworkEvent.Context> contextSupplier) {
        Level level = Minecraft.getInstance().level;
        if (level != null) {
            ParticleSpawner.spawnParticle(level, this.position, ParticleIDInfo.getScaleData(this.particleIDIndex),
                    ParticleIDInfo.getTransData(this.particleIDIndex), ParticleIDInfo.getColorData(this.particleIDIndex),
                    ParticleIDInfo.getSpinData(this.particleIDIndex));
        }
    }
}
