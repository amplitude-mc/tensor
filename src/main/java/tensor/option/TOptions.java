package tensor.option;

import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.text.TranslatableText;

import static tensor.Tensor.settingsManager;

public class TOptions
{
    public static final CyclingOption<Boolean> MINCERAFT = CyclingOption.create("visuals.minceraft", (gameOptions) ->
    {
        return TensorOptions.minceraft;
    },
    (gameOptions, option, value) ->
    {
        settingsManager.setSetting("minceraft", value.toString());
    });
    
    public static final CyclingOption<Boolean> MAC_SCROLLING_FIX = CyclingOption.create("fixes.mac_scrolling_fix", (gameOptions) ->
    {
        return TensorOptions.macScrollingFix;
    },
    (gameOptions, option, value) ->
    {
        settingsManager.setSetting("macScrollingFix", value.toString());
    });
    
    public static final CyclingOption<Boolean> PERMANENT_SPRINT = CyclingOption.create("utility.permanent_sprint", (gameOptions) ->
    {
        return TensorOptions.permanentSprint;
    },
    (gameOptions, option, value) ->
    {
        settingsManager.setSetting("permanentSprint", value.toString());
    });
    
    public static final DoubleOption ZOOM_FOV = new DoubleOption("utility.zoom_fov", 5.0D, 69.0D, 1.0F, (gameOptions) ->
    {
        return TensorOptions.zoomFOV;
    }, (gameOptions, value) ->
    {
        settingsManager.setSetting("zoomFOV", value.toString());
    }, (gameOptions, option) ->
    {
        return new TranslatableText("utility.zoom_fov.value", TensorOptions.zoomFOV);
    });
    
    public static final CyclingOption<Boolean> NO_PORTAL_GUI_CLOSING = CyclingOption.create("miscellaneous.no_portal_gui_closing", (gameOptions) ->
    {
        return TensorOptions.noPortalGUIClosing;
    },
    (gameOptions, option, value) ->
    {
        settingsManager.setSetting("noPortalGUIClosing", value.toString());
    });
    
    public static final DoubleOption ITEM_USE_COOLDOWN = new DoubleOption("utility.item_use_cooldown", 1.0D, 10.0D, 1.0F, (gameOptions) ->
    {
        return TensorOptions.itemUseCooldown;
    }, (gameOptions, value) ->
    {
        settingsManager.setSetting("itemUseCooldown", value.toString());
    }, (gameOptions, option) ->
    {
        return new TranslatableText("utility.item_use_cooldown", TensorOptions.itemUseCooldown);
    });
    
    public static final CyclingOption<Boolean> ITEM_USE_COOLDOWN_OVERRIDE_TOGGLE = CyclingOption.create("utility.item_use_cooldown_override_toggle", (gameOptions) ->
    {
        return TensorOptions.itemUseCooldownOverrideToggle;
    },
    (gameOptions, option, value) ->
    {
        settingsManager.setSetting("itemUseCooldownOverrideToggle", value.toString());
    });
    
    public static final CyclingOption<Boolean> YOUTUBE_SUB_COUNT = CyclingOption.create("visuals.youtube_sub_count", (gameOptions) ->
    {
        return TensorOptions.subCounter;
    }, (gameOptions, option, value) ->
    {
        settingsManager.setSetting("subCounter", value.toString());
    });
    
    public static final CyclingOption<Boolean> SPRINTING_TEXT = CyclingOption.create("visuals.sprinting_text", (gameOptions) ->
    {
        return TensorOptions.sprintingText;
    }, (gameOptions, option, value) ->
    {
        settingsManager.setSetting("sprintingText", value.toString());
    });
}
