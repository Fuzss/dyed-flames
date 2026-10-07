package fuzs.dyedflames.common.client.particle;

import fuzs.dyedflames.common.core.particles.FireParticleOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Util;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * @see net.minecraft.client.particle.LavaParticle.Provider
 */
public class FireParticleProvider implements ParticleProvider<FireParticleOption> {
    @Override
    public @Nullable Particle createParticle(FireParticleOption options, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, RandomSource random) {
        List<Identifier> textures = options.definition().textures();
        Identifier texture = Util.getRandom(textures, random);
        TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasManager()
                .getAtlasOrThrow(AtlasIds.PARTICLES)
                .getSprite(texture);
        return new LavaParticle(level, x, y, z, sprite);
    }
}
