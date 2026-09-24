package fuzs.dyedflames.common.data.client;

import fuzs.dyedflames.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.data.v3.particles.AbstractParticleProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModParticleProvider extends AbstractParticleProvider {

    public ModParticleProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addParticles() {
        this.add(ModRegistry.SOUL_LAVA_PARTICLE_TYPE.value());
    }
}
