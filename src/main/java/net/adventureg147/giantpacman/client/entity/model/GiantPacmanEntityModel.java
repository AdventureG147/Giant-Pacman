package net.adventureg147.giantpacman.client.entity.model;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.entity.GiantPacmanEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

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

	@SuppressWarnings("unchecked")
	@Override
	public void setLivingAnimations(GiantPacmanEntity entity, Integer uniqueID, @SuppressWarnings("rawtypes") AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		IBone top = this.getAnimationProcessor().getBone("top");
		IBone bottom = this.getAnimationProcessor().getBone("bottom");
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
		top.setScaleX(6.0f);
		top.setScaleY(6.0f);
		top.setScaleZ(6.0f);
		bottom.setScaleX(6.0f);
		bottom.setScaleY(6.0f);
		bottom.setScaleZ(6.0f);
	}
}
