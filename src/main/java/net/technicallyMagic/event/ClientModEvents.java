package net.technicallyMagic.event;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.api.layered.modifier.MirrorModifier;
import dev.kosmx.playerAnim.api.layered.modifier.SpeedModifier;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.entity.ModEntities;
import net.technicallyMagic.item.ModItems;

@Mod.EventBusSubscriber(modid = TechnicallyMagic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        //Setup item predicates for model swapping
        //Not used right now (could later)
        event.enqueueWork(() -> {
            ItemProperties.register(ModItems.STAFF1_TEST.get(), new ResourceLocation("idle_state"), (stack, level, living, id) -> {
                return 0.4f; //Would point to a class variable that changes during runtime (use ModEvents to do so)
            });
        });

        //Setup player animation stuffs
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(new ResourceLocation("technicallymagic", "animation"), 42, (LocalPlayer) -> {
            //animationStack.addAnimLayer(42, testAnimation); //Add and save the animation container for later use.
            ModifierLayer<IAnimation> testAnimation =  new ModifierLayer<>();

            testAnimation.addModifierBefore(new SpeedModifier(0.5f)); //This will be slow
            testAnimation.addModifierBefore(new MirrorModifier(true)); //Mirror the animation
            return testAnimation;
        });

        PlayerAnimationAccess.REGISTER_ANIMATION_EVENT.register((LocalPlayer, AnimationStack) -> {
            ModifierLayer<IAnimation> layer = new ModifierLayer<>();
            AnimationStack.addAnimLayer(69, layer);
            PlayerAnimationAccess.getPlayerAssociatedData(LocalPlayer).set(new ResourceLocation("technicallymagic", "animation"), layer);
        });

        EntityRenderers.register(ModEntities.KILL_PROJECTILE.get(), ThrownItemRenderer::new);
        //EntityRenderers.register(ModEntities.BEAM_PROJECTILE.get(), EntityRenderers::new);

    }









}