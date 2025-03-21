package net.technicallyMagic.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.network.packets.S2AParticleSpawnPacket;

public class PacketHandler {

    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            new ResourceLocation(TechnicallyMagic.MOD_ID, "main"))
            .serverAcceptedVersions(n -> true)
            .clientAcceptedVersions(n -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    public static void sendToPlayer(Object msg, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void sendToAllPlayers(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static void register() {
        INSTANCE.messageBuilder(S2AParticleSpawnPacket.class, 1, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(S2AParticleSpawnPacket::encode)
                .decoder(S2AParticleSpawnPacket::new)
                .consumerMainThread(S2AParticleSpawnPacket::handle)
                .add();

    }




}
