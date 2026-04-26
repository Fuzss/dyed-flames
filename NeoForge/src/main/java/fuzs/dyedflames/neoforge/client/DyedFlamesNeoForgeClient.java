package fuzs.dyedflames.neoforge.client;

import fuzs.dyedflames.common.DyedFlames;
import fuzs.dyedflames.common.client.DyedFlamesClient;
import fuzs.dyedflames.common.data.client.ModParticleProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = DyedFlames.MOD_ID, dist = Dist.CLIENT)
public class DyedFlamesNeoForgeClient {

    public DyedFlamesNeoForgeClient() {
        ClientModConstructor.construct(DyedFlames.MOD_ID, DyedFlamesClient::new);
        DataProviderHelper.registerDataProviders(DyedFlames.MOD_ID, ModParticleProvider::new);
    }
}
