package org.samo_lego.froggy_flies.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

import static org.samo_lego.froggy_flies.FroggyFlies.MOD_ID;

@Environment(EnvType.CLIENT)
public class FroggyFliesClient implements ClientModInitializer {


    public static final SimpleParticleType FIREFLY = FabricParticleTypes.simple();

    @Override
    public void onInitializeClient() {
        ClientSpriteRegistryCallback.event(InventoryMenu.BLOCK_ATLAS).register(((atlasTexture, registry) -> {
            registry.register(new ResourceLocation(MOD_ID, "particle/firefly"));
        }));

        ParticleFactoryRegistry.getInstance().register(FIREFLY, FireflyParticle.Provider::new);

        Registry.register(Registry.PARTICLE_TYPE, new ResourceLocation(MOD_ID, "firefly"), FIREFLY);
    }
}
