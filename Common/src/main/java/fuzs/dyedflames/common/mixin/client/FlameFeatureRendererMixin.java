package fuzs.dyedflames.common.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import fuzs.dyedflames.common.client.handler.ColoredFireOverlayHandler;
import fuzs.dyedflames.common.world.level.block.FireType;
import net.minecraft.client.renderer.feature.FeatureFrameContext;
import net.minecraft.client.renderer.feature.FlameFeatureRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(FlameFeatureRenderer.class)
abstract class FlameFeatureRendererMixin {

    @ModifyArgs(method = "buildGroup",
                at = @At(value = "INVOKE",
                         target = "Lnet/minecraft/client/renderer/feature/FlameFeatureRenderer;prepare(Lnet/minecraft/client/renderer/feature/FlameFeatureRenderer$Submit;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V"))
    protected void buildGroup(Args args, FeatureFrameContext context, List<FlameFeatureRenderer.Submit> submits, @Local FlameFeatureRenderer.Submit submit) {
        ColoredFireOverlayHandler.getFireOverlaySprite(submit.entityRenderState(), FireType::texture0)
                .map(context.atlasManager()::get)
                .ifPresent((TextureAtlasSprite sprite) -> args.set(2, sprite));
        ColoredFireOverlayHandler.getFireOverlaySprite(submit.entityRenderState(), FireType::texture1)
                .map(context.atlasManager()::get)
                .ifPresent((TextureAtlasSprite sprite) -> args.set(3, sprite));
    }
}
