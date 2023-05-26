package net.adventureg147.giantpacman.common.events;

import net.adventureg147.giantpacman.common.registry.GPEntityTypes;
import net.adventureg147.giantpacman.common.entity.GiantPacmanEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EntitySetAttributeEventSubscriber {
	@SubscribeEvent
	public static void onEntityAttributeCreationEvent(EntityAttributeCreationEvent event) {
		event.put(GPEntityTypes.GIANT_PACMAN.get(), GiantPacmanEntity.setCustomAttributes().build());
	}
}
