package net.technicallyMagic.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ENCHANCIUM_CARROT = new FoodProperties.Builder().nutrition(3).alwaysEat()
            .saturationMod(10.0f).effect(() -> new MobEffectInstance(MobEffects.LUCK, 200, 2), 1.0f).build();
}
