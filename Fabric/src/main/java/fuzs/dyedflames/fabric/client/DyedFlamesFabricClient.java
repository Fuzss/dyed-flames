package fuzs.dyedflames.fabric.client;

import fuzs.dyedflames.common.DyedFlames;
import fuzs.dyedflames.common.client.DyedFlamesClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class DyedFlamesFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(DyedFlames.MOD_ID, DyedFlamesClient::new);
    }
}
