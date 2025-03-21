package net.technicallyMagic.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.item.custom.KillProjectile;
import net.technicallyMagic.item.custom.checkValidBlockItem;
import net.technicallyMagic.item.custom.staffOneItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TechnicallyMagic.MOD_ID);

    public static final RegistryObject<Item> ELDER_COPPER = ITEMS.register("elder_copper",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RUNESTONE = ITEMS.register("runestone",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ETHERSHARD = ITEMS.register("ethershard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> WYRMSHARD = ITEMS.register("wyrmshard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THAUMIC_METAL_INGOT = ITEMS.register("thaumic_metal_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ENCHANCIUM_INGOT = ITEMS.register("enchancium_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> AETHERITE_INGOT = ITEMS.register("aetherite_ingot",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLOCK_DETECTOR = ITEMS.register("block_detector",
            () -> new checkValidBlockItem(new Item.Properties()));

    public static final RegistryObject<Item> ENCHANCIUM_CARROT = ITEMS.register("enchancium_carrot",
            () -> new Item(new Item.Properties().food(ModFoods.ENCHANCIUM_CARROT)));

    public static final RegistryObject<Item> STAFF1_TEST = ITEMS.register("staff1_test",
            () -> new staffOneItem(new Item.Properties()));

    public static final RegistryObject<Item> KILL_PROJECTILE = ITEMS.register("kill_projectile",
            () -> new KillProjectile(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
