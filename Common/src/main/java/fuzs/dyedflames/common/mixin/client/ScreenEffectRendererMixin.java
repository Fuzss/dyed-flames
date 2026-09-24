package fuzs.dyedflames.common.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import fuzs.dyedflames.common.client.handler.ColoredFireOverlayHandler;
import fuzs.dyedflames.common.world.level.block.FireType;
import net.minecraft.client.renderer.ScreenEffectRenderer;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ScreenEffectRenderer.class)
abstract class ScreenEffectRendererMixin {

    @ModifyArg(method = "submit",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/client/resources/model/sprite/SpriteGetter;get(Lnet/minecraft/client/resources/model/sprite/SpriteId;)Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;"),
               index = 0)
    public SpriteId submit(SpriteId spriteId, @Local(argsOnly = true) PlayerRenderState playerRenderState) {
        return ColoredFireOverlayHandler.getFireOverlaySprite(playerRenderState, FireType::texture1).orElse(spriteId);
    }
}
