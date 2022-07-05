package tensor;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tensor.command.RickrollCommand;
import tensor.hud.Drawers;
import tensor.option.SettingsManager;
import tensor.option.TensorOptions;
import tensor.util.KeyBindManager;
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
        this.loadSettings();
        this.startThreads();
        this.startDrawers();
        this.loadCommands();
        this.loadKeyBinds();
    }
    
    private void loadSettings()
    {
        Tensor.settingsManager = new SettingsManager("tensor");
        Tensor.settingsManager.parseSettingsClass(TensorOptions.class);
        Tensor.settingsManager.loadSettings();
    }
    
    private void startThreads()
    {
        new SubGetterThread();
    }
    
    private void startDrawers()
    {
        ClientTickEvents.START_CLIENT_TICK.register((client) ->
        {
            if(!this.drawersDone)
            {
                Drawers.drawAll();
                drawersDone = true;
            }
        });
    }
    
    private void loadCommands()
    {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
        {
            RickrollCommand.register(dispatcher);
        });
    }
    
    private void loadKeyBinds()
    {
        ClientTickEvents.END_CLIENT_TICK.register((c) ->
        {
            KeyBindManager.zoom(TensorOptions.zoomKey.isPressed());
            KeyBindManager.openGUI(TensorOptions.openGUIKey.isPressed());
            KeyBindManager.freeLook(TensorOptions.freeLookKey.isPressed());
            KeyBindManager.itemUseCooldownOverride(TensorOptions.itemUseCooldownOverrideKey.isPressed());
            KeyBindManager.jumpCooldownOverride(TensorOptions.jumpCooldownOverrideKey.isPressed());
        });
    }
}
