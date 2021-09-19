package tensor;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tensor.command.RickrollCommand;
import tensor.hud.Drawers;
import tensor.option.SettingsManager;
import tensor.option.TensorOptions;
import tensor.util.CPSCounter;
import tensor.util.SubGetterThread;

public class Tensor implements ClientModInitializer
{
    public static SettingsManager settingsManager;
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static Logger logger = LogManager.getLogger("tensor");
    
    @Override
    public void onInitializeClient()
    {
        settingsManager = new SettingsManager("tensor");
        settingsManager.parseSettingsClass(TensorOptions.class);
        settingsManager.loadSettings();
        new SubGetterThread();
        Drawers.drawAll();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
        {
            RickrollCommand.register(ClientCommandManager.DISPATCHER);
        });
    }
}
