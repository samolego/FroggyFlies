package org.samo_lego.froggy_flies.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.level.Level;
import org.samo_lego.froggy_flies.client.ClientWrapper;
import org.samo_lego.froggy_flies.client.FireflyParticle;
import org.samo_lego.froggy_flies.client.FroggyFliesClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Frog.class)
public class FrogMixin extends Mob {

    private final Frog self = (Frog) (Object) this;
    private final MinecraftServer server = this.level.getServer();

    public FrogMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void froggyFlies_tick(CallbackInfo ci) {
        if (this.random.nextBoolean() && this.level.getEntitiesOfClass(Frog.class, this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D)).size() < 3) {
            if (server != null && server.isDedicatedServer()) {
                // Vanilla particles
                SimpleParticleType particle = ParticleTypes.WHITE_ASH;
                for (int i = 0; i < 2; ++i) {
                    ((ServerLevel) this.level).sendParticles(particle,
                            this.getRandomX(8.0),
                            this.getRandomY() + this.random.nextDouble() * 8.0,
                            this.getRandomZ(8.0),
                            1,
                            (this.random.nextDouble() - 0.5) * 2.0,
                            -this.random.nextDouble(),
                            (this.random.nextDouble() - 0.5) * 2.0,
                            1.0);
                    particle = ParticleTypes.ASH;
                }
            } else {
                // Custom particles
                this.level.addParticle(ClientWrapper.getParticle(),
                        this.getRandomX(8.0),
                        this.getRandomY() + this.random.nextDouble() * 8.0,
                        this.getRandomZ(8.0),
                        0.0, 1.0, 0.0);

            }

            // Fake eating
            if (this.random.nextFloat() < 0.005 && this.goalSelector.getRunningGoals().findAny().isEmpty()) {
                self.setPose(Pose.USING_TONGUE);
                this.level.playSound(null, self, SoundEvents.FROG_TONGUE, SoundSource.NEUTRAL, 2.0f, 1.0f);
            }
        }
    }
}
