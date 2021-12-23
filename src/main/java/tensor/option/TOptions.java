package tensor.option;

import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.text.TranslatableText;
import tensor.util.ControlStuff;

import static tensor.Tensor.settingsManager;

public class TOptions
{
    public static final CyclingOption<Boolean> MINCERAFT = CyclingOption.create("visuals.minceraft",
        (gameOptions) -> TensorOptions.minceraft,
        (gameOptions, option, value) -> settingsManager.setSetting("minceraft", value.toString()));
    
    public static final CyclingOption<Boolean> MAC_SCROLLING_FIX = CyclingOption.create("fixes.mac_scrolling_fix",
        (gameOptions) -> TensorOptions.macScrollingFix,
        (gameOptions, option, value) -> settingsManager.setSetting("macScrollingFix", value.toString()));
    
    public static final CyclingOption<Boolean> PERMANENT_SPRINT = CyclingOption.create("utility.permanent_sprint",
        (gameOptions) -> TensorOptions.permanentSprint,
        (gameOptions, option, value) -> settingsManager.setSetting("permanentSprint", value.toString()));
    
    public static final DoubleOption ZOOM_FOV = new DoubleOption("utility.zoom_fov", 5.0D, 69.0D, 1.0F,
        (gameOptions) -> TensorOptions.zoomFOV,
        (gameOptions, value) -> settingsManager.setSetting("zoomFOV", value.toString()),
        (gameOptions, option) -> new TranslatableText("utility.zoom_fov", TensorOptions.zoomFOV));
    
    public static final CyclingOption<Boolean> NO_PORTAL_GUI_CLOSING = CyclingOption.create("miscellaneous.no_portal_gui_closing",
        (gameOptions) -> TensorOptions.noPortalGUIClosing,
        (gameOptions, option, value) -> settingsManager.setSetting("noPortalGUIClosing", value.toString()));
    
    public static final DoubleOption ITEM_USE_COOLDOWN = new DoubleOption("utility.item_use_cooldown", 1.0D, 10.0D, 1.0F,
        (gameOptions) -> TensorOptions.itemUseCooldown,
        (gameOptions, value) -> settingsManager.setSetting("itemUseCooldown", value.toString()),
        (gameOptions, option) -> new TranslatableText("utility.item_use_cooldown", TensorOptions.itemUseCooldown));
    
    public static final CyclingOption<Boolean> ITEM_USE_COOLDOWN_OVERRIDE_TOGGLE = CyclingOption.create("utility.item_use_cooldown_override_toggle",
        (gameOptions) -> TensorOptions.itemUseCooldownOverrideToggle,
        (gameOptions, option, value) -> settingsManager.setSetting("itemUseCooldownOverrideToggle", value.toString()));
    
    public static final CyclingOption<Boolean> YOUTUBE_SUB_COUNT = CyclingOption.create("visuals.youtube_sub_count",
        (gameOptions) -> TensorOptions.subCounter,
        (gameOptions, option, value) -> settingsManager.setSetting("subCounter", value.toString()));
    
    public static final CyclingOption<Boolean> SPRINTING_TEXT = CyclingOption.create("visuals.sprinting_text",
        (gameOptions) -> TensorOptions.sprintingText,
        (gameOptions, option, value) -> settingsManager.setSetting("sprintingText", value.toString()));
    
    public static final CyclingOption<Boolean> WASD = CyclingOption.create("keystrokes.wasd",
        (gameOptions) -> TensorOptions.wASD,
        (gameOptions, option, value) -> settingsManager.setSetting("wASD", value.toString()));
    
    public static final CyclingOption<Boolean> MOUSE = CyclingOption.create("keystrokes.mouse",
        (gameOptions) -> TensorOptions.mouse,
        (gameOptions, option, value) -> settingsManager.setSetting("mouse", value.toString()));
    
    public static final CyclingOption<Boolean> SPACE = CyclingOption.create("keystrokes.space",
        (gameOptions) -> TensorOptions.space,
        (gameOptions, option, value) -> settingsManager.setSetting("space", value.toString()));
    
    public static final CyclingOption<Boolean> SHIFT = CyclingOption.create("keystrokes.shift",
        (gameOptions) -> TensorOptions.shift,
        (gameOptions, option, value) -> settingsManager.setSetting("shift", value.toString()));
    
    public static final CyclingOption<Boolean> CPS = CyclingOption.create("keystrokes.cps",
        (gameOptions) -> TensorOptions.cPS,
        (gameOptions, option, value) -> settingsManager.setSetting("cPS", value.toString()));
    
    public static final CyclingOption<Boolean> CPS_TEXT = CyclingOption.create("keystrokes.cps_text",
        (gameOptions) -> TensorOptions.cPSText,
        (gameOptions, option, value) -> settingsManager.setSetting("cPSText", value.toString()));
    
    public static final CyclingOption<Boolean> FORWARD_CONTROL = CyclingOption.create("control.forward",
        (gameOptions) -> ControlStuff.forwardControl,
        (gameOptions, option, value) -> ControlStuff.forwardControl = !ControlStuff.forwardControl);
    
    public static final CyclingOption<Boolean> BACKWARD_CONTROL = CyclingOption.create("control.backward",
        (gameOptions) -> ControlStuff.backwardControl,
        (gameOptions, option, value) -> ControlStuff.backwardControl = !ControlStuff.backwardControl);
    
    public static final CyclingOption<Boolean> LEFT_CONTROL = CyclingOption.create("control.left",
        (gameOptions) -> ControlStuff.leftControl,
        (gameOptions, option, value) -> ControlStuff.leftControl = !ControlStuff.leftControl);
    
    public static final CyclingOption<Boolean> RIGHT_CONTROL = CyclingOption.create("control.right",
        (gameOptions) -> ControlStuff.rightControl,
        (gameOptions, option, value) -> ControlStuff.rightControl = !ControlStuff.rightControl);
    
    public static final CyclingOption<Boolean> JUMP_CONTROL = CyclingOption.create("control.jump",
        (gameOptions) -> ControlStuff.jumpControl,
        (gameOptions, option, value) -> ControlStuff.jumpControl = !ControlStuff.jumpControl);
    
    public static final CyclingOption<Boolean> SNEAK_CONTROL = CyclingOption.create("control.sneak",
        (gameOptions) -> ControlStuff.sneakControl,
        (gameOptions, option, value) -> ControlStuff.sneakControl = !ControlStuff.sneakControl);
}
