package net.adventureg147.giantpacman.common.entity;

import net.adventureg147.giantpacman.GiantPacman;
import net.adventureg147.giantpacman.common.registry.GPBlocks;
import net.adventureg147.giantpacman.common.registry.GPSoundEvents;
import net.adventureg147.giantpacman.common.registry.GPTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.ServerStatisticsManager;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GiantPacmanEntity extends MonsterEntity implements IAnimatable {
	private final AnimationFactory factory = new AnimationFactory(this);

	public GiantPacmanEntity(EntityType<? extends MonsterEntity> type, World world) {
		super(type, world);
	}

	public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 100.0D)
				.add(Attributes.ATTACK_SPEED, 1)
				.add(Attributes.ARMOR, 10D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.ATTACK_DAMAGE, 30.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 64.0D);
	}

	private <T extends IAnimatable> PlayState predicate(AnimationEvent<T> event) {
		event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.giant_pacman.chomp", ILoopType.EDefaultLoopTypes.LOOP));
		return PlayState.CONTINUE;
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal( 3, new NearestAttackableTargetGoal<>( this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PigEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, CowEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, SheepEntity.class, true));
	}

	@Override
	protected SoundEvent getDeathSound() {
		return GPSoundEvents.GIANT_PACMAN_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return GPSoundEvents.GIANT_PACMAN_HURT.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(GPSoundEvents.GIANT_PACMAN_LIVING.get(), 0.20F, 1.F);
	}

	@Override
	protected int getExperienceReward(PlayerEntity pPlayer) {
		return 10000;
	}

	@Override
	public void aiStep() {
		super.aiStep();

		boolean flag = false;
		AxisAlignedBB axisAlignedBB = this.getBoundingBox().inflate(0.2D, -0.5, 0.2D);

		if (this.horizontalCollision && ForgeEventFactory.getMobGriefingEvent(level, this)) {
			for (BlockPos blockPos : BlockPos.betweenClosed(MathHelper.floor(axisAlignedBB.minX),
					MathHelper.floor(axisAlignedBB.minY), MathHelper.floor(axisAlignedBB.minZ),
					MathHelper.floor(axisAlignedBB.maxX), MathHelper.floor(axisAlignedBB.maxY),
					MathHelper.floor(axisAlignedBB.maxZ))) {
				BlockState blockState = this.level.getBlockState(blockPos);
				Block block = blockState.getBlock();
				if (!(block.getExplosionResistance() >= 1400F)) {
					flag = this.level.destroyBlock(blockPos, false, this) || flag;
				}
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);
		Entity player = cause.getEntity();
		BlockPos blockPos = this.blockPosition();

		if (player instanceof ServerPlayerEntity) {
			PlayerAdvancements advancements = ((ServerPlayerEntity) player).getAdvancements();
			AdvancementManager manager = ((ServerWorld) player.getCommandSenderWorld()).getServer().getAdvancements();
			Advancement kill_pacman = manager.getAdvancement(new ResourceLocation(GiantPacman.MODID, "kill_pacman"));
			assert kill_pacman != null;
			advancements.award(kill_pacman, "impossible");
			if (getKillCount((ServerPlayerEntity) player, this.getType()) >= 255) {
				Advancement level_completed = manager.getAdvancement(new ResourceLocation(GiantPacman.MODID, "level_completed"));
				assert level_completed != null;
				advancements.award(level_completed, "impossible");
			}


			if (getKillCount((ServerPlayerEntity) player, this.getType()) == 10) {
				ItemStack trophy = new ItemStack(GPBlocks.PACMAN_TROPHY.get());
				this.level.addFreshEntity(new ItemEntity(this.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), trophy));
			}

			if (getKillCount((ServerPlayerEntity) player, this.getType()) == 100) {
				ItemStack golden_trophy = new ItemStack(GPBlocks.GOLDEN_PACMAN_TROPHY.get());
				this.level.addFreshEntity(new ItemEntity(this.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), golden_trophy));
			}
		}

		for (int i = 0; i < 10; i++) {
			this.level.addFreshEntity(new ItemEntity(this.level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), randomItem()));
		}
	}

	public ItemStack randomItem() {
		List<Item> itemList = new ArrayList<>(ForgeRegistries.ITEMS.getValues());
		itemList.removeIf(item -> item.is(GPTags.Items.UNDROPPABLE) || Objects.requireNonNull(item.getRegistryName()).getPath().contains("spawn_egg"));
		Item randomItem = itemList.get(random.nextInt(itemList.size()));

		return randomItem.getDefaultInstance();
	}

	public int getKillCount(ServerPlayerEntity player, EntityType<?> entity) {
		ServerStatisticsManager statisticsManager = player.getStats();
		Stat<EntityType<?>> statType = Stats.ENTITY_KILLED.get(entity);
		return statisticsManager.getValue(statType);
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<>(this,
				"giantpacmancontroller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}
}
