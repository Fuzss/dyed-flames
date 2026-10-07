# Dyed Flames

A Minecraft mod. Downloads can be found on [CurseForge](https://www.curseforge.com/members/fuzs_/projects)
and [Modrinth](https://modrinth.com/user/Fuzs).

![](banner.png)

## Configuration

This whole mod is fully data-driven using [data packs](https://minecraft.wiki/w/Data_pack). Fire behavior is mapped to blocks through the
`dyedflames:fire_types` [data map](https://docs.neoforged.net/docs/resources/server/datamaps/), which allows custom fire
blocks to define their own overlay textures and burning particles without any code.

Data map path:

```text
data/dyedflames/data_maps/block/fire_types.json
```

| Field      | Required | Description                                                                                                          |
|------------|----------|----------------------------------------------------------------------------------------------------------------------|
| `fluid`    | No       | A fluid tag. When set, the fire type only applies while an entity is inside that fluid, e.g. `minecraft:lava`.       |
| `texture0` | Yes      | First fire overlay texture, e.g. `minecraft:block/fire_0`. Points to `assets/<namespace>/textures/block/<path>.png`. |
| `texture1` | Yes      | Second fire overlay texture, e.g. `minecraft:block/fire_1`.                                                          |
| `particle` | No       | The particle spawned while an entity is burning. See [Particles](#particles).                                        |

`texture0` and `texture1` are the two frames used for the flaming overlay on entities and in first person.

### Particles

`particle` is optional and supports two forms.

#### Registered particle type

References a particle type by id, wrapped in an object:

```json
{
  "particle": {
    "type": "minecraft:lava"
  }
}
```

Only simple particle types are supported. Any simple particle type registered by any mod can be used.

#### Custom particle textures

Instead of a particle type, a list of particle textures can be supplied directly. Dyed Flames renders them with its own
generic, lava-like particle, so no particle type has to be registered:

```json
{
  "particle": {
    "textures": [
      "dyedflames:soul_lava_0",
      "dyedflames:soul_lava_1",
      "dyedflames:soul_lava_2"
    ]
  }
}
```

Each entry resolves to `assets/<namespace>/textures/particle/<path>.png`.
A random texture is chosen for every spawned particle.

Additionally, the bare particle type id is also still read:

```json
{
  "particle": "minecraft:lava"
}
```

### Example

```json
{
  "values": {
    "minecraft:soul_fire": {
      "texture0": "minecraft:block/soul_fire_0",
      "texture1": "minecraft:block/soul_fire_1",
      "particle": {
        "textures": [
          "dyedflames:soul_lava_0",
          "dyedflames:soul_lava_1",
          "dyedflames:soul_lava_2"
        ]
      }
    }
  }
}
```

By default `minecraft:fire`, `minecraft:lava` (restricted to the `minecraft:lava` fluid tag) and `minecraft:soul_fire`
are configured. Data pack entries for the same block override these defaults.
