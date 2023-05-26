package net.adventureg147.giantpacman.common.registry;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.entity.GiantPacmanEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GPEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, GiantPacman.MODID);

	public static final RegistryObject<EntityType<GiantPacmanEntity>> GIANT_PACMAN = ENTITY_TYPES.register("giant_pacman",
			() -> EntityType.Builder.of(GiantPacmanEntity::new, EntityClassification.MONSTER)
					.sized(2f, 2f)
					.build(new ResourceLocation(GiantPacman.MODID, "giant_pacman").toString()));
}
