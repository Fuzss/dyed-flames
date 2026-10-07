package fuzs.dyedflames.common.core.particles;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

import java.util.List;

public record ParticleSprites(List<Identifier> textures) {
    public static final MapCodec<ParticleSprites> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Identifier.CODEC.listOf().fieldOf("textures").forGetter(ParticleSprites::textures))
            .apply(instance, ParticleSprites::new));
    public static final StreamCodec<ByteBuf, ParticleSprites> STREAM_CODEC = Identifier.STREAM_CODEC.apply(ByteBufCodecs.list())
            .map(ParticleSprites::new, ParticleSprites::textures);
}
