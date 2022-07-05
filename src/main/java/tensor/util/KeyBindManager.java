package tensor.util;

import net.minecraft.client.option.Perspective;
import net.minecraft.text.Text;
import tensor.Tensor;
import tensor.gui.TensorOptionsScreen;
import tensor.option.TensorOptions;

public class KeyBindManager
{
    private static boolean zooming = false;
    private static boolean originalSmooth = Tensor.client.options.smoothCameraEnabled;
    private static boolean freeLooking = false;
    private static boolean itemUseCooldownOverrideToggleHeld = false;
    private static boolean jumpCooldownOverrideToggleHeld = false;
    
    public static void zoom(boolean pressed)
    {
        if(pressed)
        {
            Tensor.client.options.smoothCameraEnabled = true;
            KeyBindManager.zooming = true;
        } else
        {
            if(KeyBindManager.zooming())
            {
                Tensor.client.options.smoothCameraEnabled = KeyBindManager.originalSmooth;
                KeyBindManager.zooming = false;
                ZoomScroller.resetScroll();
            }
            KeyBindManager.originalSmooth = Tensor.client.options.smoothCameraEnabled;
        }
    }
    
    public static void openGUI(boolean pressed)
    {
        if(pressed && Tensor.client.isRunning() && Tensor.client.currentScreen == null)
            Tensor.client.setScreen(new TensorOptionsScreen(null, Tensor.client.options));
    }
    
    public static void freeLook(boolean pressed)
    {
        if(pressed)
        {
            Tensor.client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
            KeyBindManager.freeLooking = true;
        } else if(KeyBindManager.freeLooking())
        {
            Tensor.client.options.setPerspective(Perspective.FIRST_PERSON);
            KeyBindManager.freeLooking = false;
        }
    }
    
    public static void itemUseCooldownOverride(boolean pressed)
    {
        if(pressed)
        {
            KeyBindManager.itemUseCooldownOverrideToggleHeld = true;
        } else if(KeyBindManager.itemUseCooldownOverrideToggleHeld())
        {
            Tensor.settingsManager.setSetting("itemUseCooldownOverrideToggle", Boolean.valueOf(!TensorOptions.itemUseCooldownOverrideToggle).toString());
            Tensor.client.inGameHud.setOverlayMessage(Text.translatable("toggle.item_use_cooldown", (TensorOptions.itemUseCooldownOverrideToggle ? "§a" : "§c") + TensorOptions.itemUseCooldownOverrideToggle), false);
            KeyBindManager.itemUseCooldownOverrideToggleHeld = false;
        }
    }
    
    public static void jumpCooldownOverride(boolean pressed)
    {
        if(pressed)
        {
            KeyBindManager.jumpCooldownOverrideToggleHeld = true;
        } else if(KeyBindManager.jumpCooldownOverrideToggleHeld())
        {
            Tensor.settingsManager.setSetting("jumpCooldownOverrideToggle", Boolean.valueOf(!TensorOptions.jumpCooldownOverrideToggle).toString());
            Tensor.client.inGameHud.setOverlayMessage(Text.translatable("toggle.jump_cooldown", (TensorOptions.jumpCooldownOverrideToggle ? "§a" : "§c") + TensorOptions.jumpCooldownOverrideToggle), false);
            KeyBindManager.jumpCooldownOverrideToggleHeld = false;
        }
    }
    
    public static boolean zooming()
    {
        return KeyBindManager.zooming;
    }
    
    public static boolean freeLooking()
    {
        return KeyBindManager.freeLooking;
    }
    
    public static boolean itemUseCooldownOverrideToggleHeld()
    {
        return KeyBindManager.itemUseCooldownOverrideToggleHeld;
    }
    
    public static boolean jumpCooldownOverrideToggleHeld()
    {
        return KeyBindManager.jumpCooldownOverrideToggleHeld;
    }
}
