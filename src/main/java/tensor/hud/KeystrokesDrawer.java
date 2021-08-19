package tensor.hud;

import net.minecraft.client.MinecraftClient;
import tensor.Tensor;

public class KeystrokesDrawer implements Drawer
{
    private static KeystrokesDrawer keystrokesDrawer;
    private MinecraftClient client;
    
    private KeystrokesDrawer(MinecraftClient client)
    {
        this.client = client;
    }
    
    @Override
    public void draw()
    {
    
    }
    
    public static KeystrokesDrawer getInstance()
    {
        if(KeystrokesDrawer.keystrokesDrawer == null)
            KeystrokesDrawer.keystrokesDrawer = new KeystrokesDrawer(Tensor.client);
        return KeystrokesDrawer.keystrokesDrawer;
    }
}
