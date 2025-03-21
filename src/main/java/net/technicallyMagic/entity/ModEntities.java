package net.technicallyMagic.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.technicallyMagic.TechnicallyMagic;
import net.technicallyMagic.entity.custom.ProjectileEntity;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TechnicallyMagic.MOD_ID);

    public static final RegistryObject<EntityType<ProjectileEntity>> KILL_PROJECTILE =
            ENTITY_TYPES.register("kill_projectile", () -> EntityType.Builder.<ProjectileEntity>of(ProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("kill_projectile"));

//    public static final RegistryObject<EntityType<BeamProjectileEntity>> BEAM_PROJECTILE =
//            ENTITY_TYPES.register("beam_projectile(uf)", () -> EntityType.Builder.of(BeamProjectileEntity::new, MobCategory.MISC)
//                    .sized(0.5f, 0.5f).build("beam_projectile(uf)"));


    public static void register(IEventBus eventBus) {ENTITY_TYPES.register(eventBus);}
}
