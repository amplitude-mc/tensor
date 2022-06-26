package tensor;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tensor.command.RickrollCommand;
import tensor.hud.Drawers;
import tensor.option.SettingsManager;
import tensor.option.TensorOptions;
import tensor.util.Key;
import tensor.util.Rectangle;
import tensor.util.SubGetterThread;

public class Tensor implements ClientModInitializer
{
    public static SettingsManager settingsManager;
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static Logger logger = LoggerFactory.getLogger("tensor");
    private boolean drawersDone = false;
    
    @Override
    public void onInitializeClient()
    {
        settingsManager = new SettingsManager("tensor");
        settingsManager.parseSettingsClass(TensorOptions.class);
        settingsManager.loadSettings();
        new SubGetterThread();
        ClientTickEvents.START_CLIENT_TICK.register((client) ->
        {
            if(!this.drawersDone)
            {
                Drawers.drawAll();
                drawersDone = true;
            }
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
        {
            RickrollCommand.register(dispatcher);
        });
    }
}
