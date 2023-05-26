package net.adventureg147.giantpacman.client.entity.model;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.entity.GiantPacmanEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GiantPacmanEntityModel extends AnimatedGeoModel<GiantPacmanEntity> {
	@Override
	public ResourceLocation getModelLocation(GiantPacmanEntity object) {
		return new ResourceLocation(GiantPacman.MODID, "geo/giant_pacman.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GiantPacmanEntity object) {
		return new ResourceLocation(GiantPacman.MODID, "textures/entity/giant_pacman.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(GiantPacmanEntity animatable) {
		return new ResourceLocation(GiantPacman.MODID, "animations/giant_pacman.animation.json");
	}
}
