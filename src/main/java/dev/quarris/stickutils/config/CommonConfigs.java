package dev.quarris.stickutils.config;

import dev.quarris.stickutils.items.CatStick;
import dev.quarris.stickutils.items.CraftingStick;
import dev.quarris.stickutils.items.CreeperStick;
import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigs {

    public static void register(ForgeConfigSpec.Builder builder) {
        builder.push("general").comment("General Configs");

        builder.pop();
        builder.push("sticks").comment("Individual Stick Configs");
        stickConfigs(builder);
        builder.pop();
    }

    private static void stickConfigs(ForgeConfigSpec.Builder builder) {
        CraftingStick.buildConfigs(builder);
        CatStick.buildConfigs(builder);
        CreeperStick.buildConfigs(builder);
    }

}
