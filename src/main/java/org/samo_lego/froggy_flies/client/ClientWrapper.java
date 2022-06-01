package org.samo_lego.froggy_flies.client;

import net.minecraft.core.particles.ParticleOptions;

import static org.samo_lego.froggy_flies.client.FroggyFliesClient.FIREFLY;

public class ClientWrapper {
    public static ParticleOptions getParticle() {
        return FIREFLY;
    }
}
