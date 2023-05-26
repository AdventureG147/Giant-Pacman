package net.adventureg147.giantpacman.client;

import net.adventureg147.giantpacman.client.entity.renderer.GiantPacmanEntityRenderer;
import net.adventureg147.giantpacman.common.registry.GPBlocks;
import net.adventureg147.giantpacman.common.registry.GPEntityTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetupEvent {
	public static void onFMLClientSetupEvent(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(GPEntityTypes.GIANT_PACMAN.get(), GiantPacmanEntityRenderer::new);

		RenderTypeLookup.setRenderLayer(GPBlocks.PACMAN_TROPHY.get(), RenderType.cutout());
		RenderTypeLookup.setRenderLayer(GPBlocks.GOLDEN_TROPHY.get(), RenderType.cutout());
	}
}
