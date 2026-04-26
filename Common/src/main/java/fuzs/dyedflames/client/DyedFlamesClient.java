package fuzs.dyedflames.client;

import fuzs.dyedflames.client.handler.ColoredFireOverlayHandler;
import fuzs.dyedflames.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.ParticleProvidersContext;
import fuzs.puzzleslib.common.api.client.event.v1.renderer.ExtractEntityRenderStateCallback;
import fuzs.puzzleslib.common.api.client.event.v1.renderer.RenderBlockOverlayCallback;
import net.minecraft.client.particle.LavaParticle;

public class DyedFlamesClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ExtractEntityRenderStateCallback.EVENT.register(ColoredFireOverlayHandler::onExtractEntityRenderState);
        RenderBlockOverlayCallback.EVENT.register(ColoredFireOverlayHandler::onRenderBlockOverlay);
    }

    @Override
    public void onRegisterParticleProviders(ParticleProvidersContext context) {
        context.registerParticleProvider(ModRegistry.SOUL_LAVA_PARTICLE_TYPE.value(), LavaParticle.Provider::new);
    }
}
