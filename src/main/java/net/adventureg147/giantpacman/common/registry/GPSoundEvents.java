package net.adventureg147.giantpacman.common.registry;

import net.adventureg147.giantpacman.GiantPacman;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GPSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GiantPacman.MODID);

	public static final RegistryObject<SoundEvent> GIANT_PACMAN_LIVING = SOUND_EVENTS.register("entity.giant_pacman.living", () -> new SoundEvent(new ResourceLocation(GiantPacman.MODID, "entity.giant_pacman.living")));
	public static final RegistryObject<SoundEvent> GIANT_PACMAN_HURT = SOUND_EVENTS.register("entity.giant_pacman.hurt", () -> new SoundEvent(new ResourceLocation(GiantPacman.MODID, "entity.giant_pacman.hurt")));
	public static final RegistryObject<SoundEvent> GIANT_PACMAN_DEATH = SOUND_EVENTS.register("entity.giant_pacman.death", () -> new SoundEvent(new ResourceLocation(GiantPacman.MODID, "entity.giant_pacman.death")));
}
