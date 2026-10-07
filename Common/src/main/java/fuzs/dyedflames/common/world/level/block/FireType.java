package fuzs.dyedflames.common.world.level.block;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fuzs.dyedflames.common.core.particles.FireParticleOption;
import fuzs.dyedflames.common.core.particles.ParticleSprites;
import fuzs.dyedflames.common.init.ModRegistry;
import fuzs.multiloaderdataextensions.common.api.v2.DataMapLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.Optional;

public record FireType(Optional<TagKey<Fluid>> fluid,
                       Identifier texture0,
                       Identifier texture1,
                       Optional<Either<SimpleParticleType, ParticleSprites>> particle) {
    public static final Codec<SimpleParticleType> PARTICLE_TYPE_CODEC = BuiltInRegistries.PARTICLE_TYPE.byNameCodec()
            .flatXmap((ParticleType<?> particleType) -> {
                return particleType instanceof SimpleParticleType simpleParticleType ?
                        DataResult.success(simpleParticleType) : DataResult.error(() -> "Unsupported type "
                        + BuiltInRegistries.PARTICLE_TYPE.getKey(particleType));
            }, DataResult::success);
    public static final Codec<FireType> CODEC = RecordCodecBuilder.create(instance -> {
        return instance.group(TagKey.codec(Registries.FLUID).optionalFieldOf("fluid").forGetter(FireType::fluid),
                Identifier.CODEC.fieldOf("texture0").forGetter(FireType::texture0),
                Identifier.CODEC.fieldOf("texture1").forGetter(FireType::texture1),
                Codec.withAlternative(Codec.either(PARTICLE_TYPE_CODEC.fieldOf("type").codec(),
                                ParticleSprites.CODEC.codec()),
                        PARTICLE_TYPE_CODEC.flatXmap((SimpleParticleType particleType) -> {
                            return DataResult.success(Either.left(particleType));
                        }, (Either<SimpleParticleType, ParticleSprites> particle) -> {
                            return particle.left().map(DataResult::success).orElseGet(() -> {
                                return DataResult.error(() -> {
                                    return "Particle is not a simple particle type";
                                });
                            });
                        })).optionalFieldOf("particle").forGetter(FireType::particle)).apply(instance, FireType::new);
    });

    public Optional<ParticleOptions> createParticleOptions() {
        return this.particle.map((Either<SimpleParticleType, ParticleSprites> either) -> {
            return either.map((SimpleParticleType particleType) -> {
                return particleType;
            }, (ParticleSprites definition) -> {
                return new FireParticleOption(ModRegistry.FIRE_PARTICLE_TYPE.value(), definition);
            });
        });
    }

    public static Optional<FireType> getFireType(Block block) {
        return Optional.ofNullable(DataMapLookup.getData(ModRegistry.FIRE_TYPES_DATA_MAP_TYPE,
                block.builtInRegistryHolder()));
    }
}
