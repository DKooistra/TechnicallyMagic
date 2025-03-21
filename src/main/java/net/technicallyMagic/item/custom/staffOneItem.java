package net.technicallyMagic.item.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.technicallyMagic.client.StaffOneItemRenderer;
import net.technicallyMagic.event.ScuffedCustomEventHandler;
import net.technicallyMagic.logic.dirVec3;
import net.technicallyMagic.logic.magicBeam;
import net.technicallyMagic.sound.ModSounds;
import net.technicallyMagic.util.Animations;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

public class staffOneItem extends Item implements GeoItem {

    public staffOneItem(Properties pProperties) {
        super(pProperties);
    }


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final RawAnimation idleAnimation = Animations.BLACK_ORB_IDLE;
    private RawAnimation itemAnimation = Animations.BLACK_ORB_IDLE;
    private boolean usingItem = false;
    private boolean animationStarted = false;
    private boolean[] stagesCompleted = {false};
    private Vec3 currentPlayerPos;
    private Vec3 currentPlayerLookAngle;
    private Player player;

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }


    // This will run for each perspective possible for the item fyi,
    // not just the current perspective
    private PlayState predicate(AnimationState animationState) {

        // Method for setting animation for third-person perspectives
        if (animationState.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) == ItemDisplayContext.THIRD_PERSON_RIGHT_HAND
                || animationState.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) == ItemDisplayContext.THIRD_PERSON_LEFT_HAND) {

            onThirdPersonAnimTick(animationState);
        }
        // Method for setting animation for first-person perspectives
        else if (animationState.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND
                || animationState.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {

            onFirstPersonAnimTick(animationState);
        }
        // Method for setting animation for GUI & Ground perspectives
        else if (animationState.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) == ItemDisplayContext.GUI
                || animationState.getData(DataTickets.ITEM_RENDER_PERSPECTIVE) == ItemDisplayContext.GROUND){
            onGGAnimTick(animationState);
        }


        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private StaffOneItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    renderer = new StaffOneItemRenderer();
                }

                return this.renderer;

            }
        });
    }

    //Allow for different animations in first-person vs third-person
    @Override
    public boolean isPerspectiveAware() {return true;}

    //End of GeckoLib Item Animation stuffs

    //Custom Item Logic

    //Called upon right click with item (anything)
    private void onUse(Level level, Player player) {

        if (usingItem) {
            return;
        }

        if (level instanceof ServerLevel) {
            ServerLevel sLevel = ServerLifecycleHooks.getCurrentServer().getLevel(Level.OVERWORLD);
            Animations.testZigysAPI(sLevel, player);


        } else {
            usingItem = true;
            Animations.playPlayerAnimation(Animations.RAISE_ARM);
            setItemAnimation(Animations.HIGH_ARM_RAISE);
            level.playSeededSound(player, player.position().x, player.position().y + 2, player.position().z,
                    ModSounds.MAGIC_BEAM.get(), SoundSource.AMBIENT, 4.5f, 1.1f, 0);
        }
    }



    // Ran for third-person item rendering perspective
    private void onThirdPersonAnimTick(AnimationState animState) {
        AnimationController controller = animState.getController();

        if (usingItem && !controller.hasAnimationFinished()) {
            if (!animState.isCurrentAnimation(itemAnimation)) {

                controller.setAnimation(itemAnimation);
                if (!animationStarted) {
                    ScuffedCustomEventHandler.startTickTracking();
                    animationStarted = true;
                } else {
                    double scalarSpeed = ScuffedCustomEventHandler.peekTick() / 164.0 + 1.0;
                    controller.setAnimationSpeed(scalarSpeed);
                }
            }
        } else {
            controller.setAnimation(idleAnimation);
            usingItem = false;
            animationStarted = false;
            controller.setAnimationSpeed(1.0);
        }

    }

    // Ran for first-person item rendering perspective
    private void onFirstPersonAnimTick(AnimationState animState) {
        AnimationController controller = animState.getController();

        if (usingItem && !controller.hasAnimationFinished()) {
            if (!animState.isCurrentAnimation(Animations.BLACK_ORB_FIRE)) {

                controller.setAnimation(Animations.BLACK_ORB_FIRE);
                if (!animationStarted) {
                    ScuffedCustomEventHandler.startTickTracking();
                    animationStarted = true;
                } else {
                    double scalarSpeed = ScuffedCustomEventHandler.peekTick() / 164.0 + 1.0;
                    controller.setAnimationSpeed(scalarSpeed);
                }
            }

            // Purple Orb Idle is a looping animation, thus needs something else to end the
            // item animation other than the animation finishing
            if (animationStarted && !ScuffedCustomEventHandler.isTracking()) {
                usingItem = false;
            }

        } else {
            controller.setAnimation(idleAnimation);
            usingItem = false;
            animationStarted = false;
            controller.setAnimationSpeed(1.0);
        }
    }

    // Ran for GUI & Ground Ticks
    private void onGGAnimTick(AnimationState animState) {
        if (animState.getController().getCurrentRawAnimation() == null) {
            animState.getController().setAnimation(idleAnimation);
        }
        animTickProcedure();
    }

    // Procedure for things outside item animations
    // Specifically: when does magic beam happen, when does animation end
    private void animTickProcedure() {

        int currentAnimTick = ScuffedCustomEventHandler.peekTick();

        if (currentAnimTick >= 157 && currentAnimTick <= 171 && ScuffedCustomEventHandler.isTracking()) {
            currentPlayerPos = new Vec3(player.position().x, player.position().y + 2, player.position().z);
            currentPlayerLookAngle = player.getLookAngle();
            dirVec3 playerLookingVector = new dirVec3(currentPlayerPos, currentPlayerLookAngle);
            ServerLevel sLevel = player.getServer().getLevel(player.level().dimension());
            magicBeam.fireMagicBeam(playerLookingVector, Minecraft.getInstance().level, sLevel);
            ScuffedCustomEventHandler.endTickTracking();
        }

    }



    //Change Item Animation Method
    private void setItemAnimation(RawAnimation animation) {
        this.itemAnimation = animation;
    }


    //Item Events

    //On Right-Click (anything)
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        this.player = pPlayer;
        onUse(pLevel, pPlayer);

        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        return InteractionResultHolder.pass(itemstack);

    }

    //Text shown on item
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.literal("Magic!"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }


}
