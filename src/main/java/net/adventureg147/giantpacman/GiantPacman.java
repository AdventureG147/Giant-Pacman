package net.adventureg147.giantpacman;

import net.adventureg147.giantpacman.client.ClientSetupEvent;
import net.adventureg147.giantpacman.common.events.EntitySetAttributeEventSubscriber;
import net.adventureg147.giantpacman.common.registry.GPBlocks;
import net.adventureg147.giantpacman.common.registry.GPEntityTypes;
import net.adventureg147.giantpacman.common.registry.GPItems;
import net.adventureg147.giantpacman.common.registry.GPSoundEvents;
import net.adventureg147.giantpacman.common.worldgen.BiomeLoadEventSubscriber;
import net.adventureg147.giantpacman.data.GPItemModelProvider;
import net.adventureg147.giantpacman.data.GPLootTableProvider;
import net.adventureg147.giantpacman.data.GPTagProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.forgespi.language.IModInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ArtifactVersion;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

import java.util.Optional;

@Mod(GiantPacman.MODID)
public class GiantPacman {
	public static final String MODID = "giantpacman";
	public static final String MODNAME = "Giant Pacman";
	public static ArtifactVersion VERSION = null;
	public static final Logger LOGGER = LogManager.getLogger();

	public GiantPacman() {
		GeckoLibMod.DISABLE_IN_DEV = true;
		GeckoLib.initialize();

		Optional<? extends ModContainer> opt = ModList.get().getModContainerById(MODID);
		if (opt.isPresent()) {
			IModInfo modInfo = opt.get().getModInfo();
			VERSION = modInfo.getVersion();
		} else {
			LOGGER.warn("Cannot get version from mod info");
		}

		LOGGER.debug(MODNAME + " Version is: " + VERSION);
		LOGGER.debug("Mod ID for " + MODNAME + " is: " + MODID);

		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.addListener(this::gatherData);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			eventBus.addListener(ClientSetupEvent::onFMLClientSetupEvent);
		}

		GPBlocks.ITEM_BLOCKS.register(eventBus);
		GPBlocks.BLOCKS.register(eventBus);
		GPEntityTypes.ENTITY_TYPES.register(eventBus);
		GPItems.ITEMS.register(eventBus);
		GPSoundEvents.SOUND_EVENTS.register(eventBus);
		eventBus.addListener(EntitySetAttributeEventSubscriber::onEntityAttributeCreationEvent);

		IEventBus forgeBus = MinecraftForge.EVENT_BUS;
		forgeBus.addListener(EventPriority.HIGH, BiomeLoadEventSubscriber::onBiomeLoadingEvent);
		forgeBus.register(this);
	}

	private void gatherData(final GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		final ExistingFileHelper existing = event.getExistingFileHelper();
		if (event.includeClient()) {
			dataGenerator.addProvider(new GPItemModelProvider(dataGenerator, existing));
		}
		if (event.includeServer()) {
			dataGenerator.addProvider(new GPLootTableProvider(dataGenerator));
			dataGenerator.addProvider(new GPTagProvider.GPBlockTagProvider(dataGenerator, existing));
			dataGenerator.addProvider(new GPTagProvider.GPItemTagProvider(dataGenerator, existing));
		}
	}
}
