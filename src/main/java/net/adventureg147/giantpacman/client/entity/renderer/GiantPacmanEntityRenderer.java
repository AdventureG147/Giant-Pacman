package net.adventureg147.giantpacman.client.entity.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.entity.GiantPacmanEntity;
import net.adventureg147.giantpacman.client.entity.model.GiantPacmanEntityModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GiantPacmanEntityRenderer extends GeoEntityRenderer<GiantPacmanEntity> {
	public GiantPacmanEntityRenderer(EntityRendererManager renderManager) {
		super(renderManager, new GiantPacmanEntityModel());
		this.shadowRadius = 3.0F;
	}

	@Override
	public void renderEarly(GiantPacmanEntity animatable, MatrixStack stackIn, float ticks, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
		super.renderEarly(animatable, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
	}

	@Override
	public ResourceLocation getTextureLocation(GiantPacmanEntity entity) {
		return new ResourceLocation(GiantPacman.MODID, "textures/entity/giant_pacman.png");
	}

	@Override
	public RenderType getRenderType(GiantPacmanEntity animatable, float partialTicks, MatrixStack stack, IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void renderRecursively(GeoBone bone, MatrixStack matrixStack, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		super.renderRecursively(bone, matrixStack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	protected float getDeathMaxRotation(GiantPacmanEntity entity) {
		return 0.0F;
	}
}
