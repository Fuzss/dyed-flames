package fuzs.dyedflames.common.core.particles;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record FireParticleOption(ParticleType<FireParticleOption> particleType,
                                 ParticleSprites definition) implements ParticleOptions {

    public static MapCodec<FireParticleOption> codec(ParticleType<FireParticleOption> particleType) {
        return ParticleSprites.CODEC.xmap((ParticleSprites particles) -> {
            return new FireParticleOption(particleType, particles);
        }, FireParticleOption::definition);
    }

    public static StreamCodec<? super RegistryFriendlyByteBuf, FireParticleOption> streamCodec(ParticleType<FireParticleOption> particleType) {
        return ParticleSprites.STREAM_CODEC.map((ParticleSprites definition) -> {
            return new FireParticleOption(particleType, definition);
        }, FireParticleOption::definition);
    }

    @Override
    public ParticleType<?> getType() {
        return this.particleType;
    }
}
