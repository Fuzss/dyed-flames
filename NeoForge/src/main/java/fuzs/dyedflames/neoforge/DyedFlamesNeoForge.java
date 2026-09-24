package fuzs.dyedflames.neoforge;

import fuzs.dyedflames.common.DyedFlames;
import fuzs.dyedflames.neoforge.data.ModDataMapProvider;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v3.core.DataProviderBuilder;
import net.neoforged.fml.common.Mod;

@Mod(DyedFlames.MOD_ID)
public class DyedFlamesNeoForge {

    public DyedFlamesNeoForge() {
        ModConstructor.construct(DyedFlames.MOD_ID, DyedFlames::new);
        DataProviderBuilder.of(DyedFlames.MOD_ID).addProvider(ModDataMapProvider::new);
    }
}
