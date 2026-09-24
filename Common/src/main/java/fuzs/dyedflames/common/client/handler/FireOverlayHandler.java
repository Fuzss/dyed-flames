package fuzs.dyedflames.common.client.handler;

import fuzs.dyedflames.common.DyedFlames;
import fuzs.dyedflames.common.init.ModRegistry;
import fuzs.dyedflames.common.world.level.block.FireType;
import fuzs.puzzleslib.common.api.client.renderer.v2.RenderStateExtraData;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.extract.LevelExtractor;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Optional;
import java.util.function.Function;

public class FireOverlayHandler {
    private static final Function<Identifier, SpriteId> FIRE_MATERIALS = Util.memoize((Identifier textureLocation) -> {
        return new SpriteId(TextureAtlas.LOCATION_BLOCKS, textureLocation);
    });
    public static final ContextKey<Block> FIRE_TYPE_KEY = new ContextKey<>(DyedFlames.id("fire_type"));

    public static void onExtractEntityRenderState(Entity entity, EntityRenderState renderState, float partialTick) {
        Block fireType = ModRegistry.FIRE_ATTACHMENT_TYPE.getOrDefault(entity, Blocks.FIRE);
        RenderStateExtraData.set(renderState, FIRE_TYPE_KEY, fireType);
    }

    public static void onExtractLevelRenderState(LevelExtractor levelExtractor, LevelRenderState renderState, ClientLevel level, Camera camera, Frustum frustum, DeltaTracker deltaTracker) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null) {
            Block fireType = ModRegistry.FIRE_ATTACHMENT_TYPE.getOrDefault(player, Blocks.FIRE);
            RenderStateExtraData.set(renderState.playerRenderState, FIRE_TYPE_KEY, fireType);
        }
    }

    public static Optional<SpriteId> getFireEntitySprite(Object state, Function<FireType, Identifier> textureGetter) {
        Block fireType = RenderStateExtraData.getOrDefault(state, FIRE_TYPE_KEY, Blocks.AIR);
        return getFireBlockSprite(fireType, textureGetter);
    }

    private static Optional<SpriteId> getFireBlockSprite(Block block, Function<FireType, Identifier> textureGetter) {
        return FireType.getFireType(block)
                .map((FireType fireType) -> FIRE_MATERIALS.apply(textureGetter.apply(fireType)));
    }
}
