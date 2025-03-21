package net.technicallyMagic.util;

import com.zigythebird.playeranimatorapi.API.PlayerAnimAPI;
import dev.kosmx.playerAnim.api.firstPerson.FirstPersonConfiguration;
import dev.kosmx.playerAnim.api.firstPerson.FirstPersonMode;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;


public class Animations {
    //GeckoLib Animations
    public static final RawAnimation BLACK_ORB_IDLE = RawAnimation.begin().then("black_orb_idle", Animation.LoopType.LOOP);
    public static final RawAnimation BLACK_ORB_FIRE = RawAnimation.begin().then("black_orb_fire", Animation.LoopType.PLAY_ONCE);
    public static final RawAnimation PURPLE_ORB_IDLE = RawAnimation.begin().then("purple_orb_idle", Animation.LoopType.LOOP);
    public static final RawAnimation HIGH_ARM_RAISE = RawAnimation.begin().then("high_arm_raise", Animation.LoopType.PLAY_ONCE);

    //Player Animations
    public static final ResourceLocation RAISE_ARM = new ResourceLocation("technicallymagic", "raise_right_arm_full");



    // Play different player animations methods

    //Play Animation with provided Resource Location
    public static void playPlayerAnimation(ResourceLocation animation) {
        ModifierLayer<IAnimation> testAnimation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData(Minecraft.getInstance().player)
                .get(new ResourceLocation("technicallymagic", "animation"));
        if (testAnimation != null) {
            testAnimation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(animation))
                    .setFirstPersonMode(FirstPersonMode.VANILLA)
                    .setFirstPersonConfiguration(new FirstPersonConfiguration().setShowRightArm(true).setShowLeftItem(true)));
        }
    }



    /*
    Method for playing player animations server side using Zigy's PlayerAnimatiorAPI
    (sending packets to all players so that player animations are visible to all players rather than just one)
     */
    public static void testZigysAPI(ServerLevel level, Player player) {
        //PlayerAnimAPI.playPlayerAnim(level, player, Animations.RAISE_ARM);

    }

}
