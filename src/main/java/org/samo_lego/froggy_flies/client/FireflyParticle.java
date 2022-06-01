package org.samo_lego.froggy_flies.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.AshParticle;
import net.minecraft.client.particle.BaseAshSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.Random;

@Environment(EnvType.CLIENT)
public class FireflyParticle extends BaseAshSmokeParticle {

    private static final Random RND = new Random();
    public FireflyParticle(ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, float j, SpriteSet spriteSet) {
        super(clientLevel, d, e, f, RND.nextFloat(), RND.nextFloat(), RND.nextFloat(), g, h, i, j, spriteSet, 0.5F, 20, 0.01F * (RND.nextBoolean() ? -1 : 1), true);
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            return new FireflyParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, 1.0F, this.sprites);
        }
    }
}
