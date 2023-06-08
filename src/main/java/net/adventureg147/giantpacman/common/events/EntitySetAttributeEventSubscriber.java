package net.adventureg147.giantpacman.common.events;

import net.adventureg147.giantpacman.common.registry.GPEntityTypes;
import net.adventureg147.giantpacman.common.entity.GiantPacmanEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySetAttributeEventSubscriber {
	@SubscribeEvent
	public static void onEntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
		event.put(GPEntityTypes.GIANT_PACMAN.get(), GiantPacmanEntity.setCustomAttributes().build());
	}
}
