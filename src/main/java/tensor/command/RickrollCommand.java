package tensor.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.util.Util;

public class RickrollCommand
{
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher)
    {
        LiteralArgumentBuilder<FabricClientCommandSource> rickroll = ClientCommandManager.literal("rickroll").
            executes((c) ->
            {
                Util.getOperatingSystem().open("https://bit.ly/IqT6zt");
                return 0;
            });
        dispatcher.register(rickroll);
    }
}
