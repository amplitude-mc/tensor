package tensor.option;

import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import tensor.util.ControlStuff;

import static tensor.Tensor.settingsManager;

public class TOptions
{
    public static final SimpleOption<Boolean> MINCERAFT = SimpleOption.ofBoolean("visuals.minceraft",
        TensorOptions.minceraft,
        (value) -> settingsManager.setSetting("minceraft", value.toString()));
    
    public static final SimpleOption<Boolean> MAC_SCROLLING_FIX = SimpleOption.ofBoolean("fixes.mac_scrolling_fix",
        TensorOptions.macScrollingFix,
        (value) -> settingsManager.setSetting("macScrollingFix", value.toString()));
    
    public static final SimpleOption<Boolean> PERMANENT_SPRINT = SimpleOption.ofBoolean("utility.permanent_sprint",
        TensorOptions.permanentSprint,
        (value) -> settingsManager.setSetting("permanentSprint", value.toString()));
    
    public static final SimpleOption<Integer> ZOOM_FOV = new SimpleOption<>("utility.zoom_fov",
        SimpleOption.emptyTooltip(),
        (optionText, value) -> Text.translatable("utility.zoom_fov", TensorOptions.zoomFOV),
        new SimpleOption.ValidatingIntSliderCallbacks(10, 70).withModifier(
            (sliderProgressValue) -> sliderProgressValue,
            (value) -> value
        ),
        TensorOptions.zoomFOV,
        (value) -> settingsManager.setSetting("zoomFOV", value.toString()));
    
    public static final SimpleOption<Boolean> NO_PORTAL_GUI_CLOSING = SimpleOption.ofBoolean("miscellaneous.no_portal_gui_closing",
        TensorOptions.noPortalGUIClosing,
        (value) -> settingsManager.setSetting("noPortalGUIClosing", value.toString()));
    
    public static final SimpleOption<Integer> ITEM_USE_COOLDOWN = new SimpleOption<>("utility.item_use_cooldown",
        SimpleOption.emptyTooltip(),
        (optionText, value) -> Text.translatable("utility.item_use_cooldown", TensorOptions.itemUseCooldown),
        new SimpleOption.ValidatingIntSliderCallbacks(1, 10).withModifier(
            (sliderProgressValue) -> sliderProgressValue,
            (value) -> value
        ),
        TensorOptions.itemUseCooldown,
        (value) -> settingsManager.setSetting("itemUseCooldown", value.toString()));
    
    public static final SimpleOption<Boolean> ITEM_USE_COOLDOWN_OVERRIDE_TOGGLE = SimpleOption.ofBoolean("utility.item_use_cooldown_override_toggle",
        TensorOptions.itemUseCooldownOverrideToggle,
        (value) -> settingsManager.setSetting("itemUseCooldownOverrideToggle", value.toString()));
    
    public static final SimpleOption<Boolean> YOUTUBE_SUB_COUNT = SimpleOption.ofBoolean("visuals.youtube_sub_count",
        TensorOptions.subCounter,
        (value) -> settingsManager.setSetting("subCounter", value.toString()));
    
    public static final SimpleOption<Boolean> SPRINTING_TEXT = SimpleOption.ofBoolean("visuals.sprinting_text",
        TensorOptions.sprintingText,
        (value) -> settingsManager.setSetting("sprintingText", value.toString()));
    
    public static final SimpleOption<Boolean> WASD = SimpleOption.ofBoolean("keystrokes.wasd",
        TensorOptions.wASD,
        (value) -> settingsManager.setSetting("wASD", value.toString()));
    
    public static final SimpleOption<Boolean> MOUSE = SimpleOption.ofBoolean("keystrokes.mouse",
        TensorOptions.mouse,
        (value) -> settingsManager.setSetting("mouse", value.toString()));
    
    public static final SimpleOption<Boolean> SPACE = SimpleOption.ofBoolean("keystrokes.space",
        TensorOptions.space,
        (value) -> settingsManager.setSetting("space", value.toString()));
    
    public static final SimpleOption<Boolean> SHIFT = SimpleOption.ofBoolean("keystrokes.shift",
        TensorOptions.shift,
        (value) -> settingsManager.setSetting("shift", value.toString()));
    
    public static final SimpleOption<Boolean> CPS = SimpleOption.ofBoolean("keystrokes.cps",
        TensorOptions.cPS,
        (value) -> settingsManager.setSetting("cPS", value.toString()));
    
    public static final SimpleOption<Boolean> CPS_TEXT = SimpleOption.ofBoolean("keystrokes.cps_text",
        TensorOptions.cPSText,
        (value) -> settingsManager.setSetting("cPSText", value.toString()));
    
    public static final SimpleOption<Boolean> FORWARD_CONTROL = SimpleOption.ofBoolean("control.forward",
        ControlStuff.forwardControl,
        (value) -> ControlStuff.forwardControl = !ControlStuff.forwardControl);
    
    public static final SimpleOption<Boolean> BACKWARD_CONTROL = SimpleOption.ofBoolean("control.backward",
        ControlStuff.backwardControl,
        (value) -> ControlStuff.backwardControl = !ControlStuff.backwardControl);
    
    public static final SimpleOption<Boolean> LEFT_CONTROL = SimpleOption.ofBoolean("control.left",
        ControlStuff.leftControl,
        (value) -> ControlStuff.leftControl = !ControlStuff.leftControl);
    
    public static final SimpleOption<Boolean> RIGHT_CONTROL = SimpleOption.ofBoolean("control.right",
        ControlStuff.rightControl,
        (value) -> ControlStuff.rightControl = !ControlStuff.rightControl);
    
    public static final SimpleOption<Boolean> JUMP_CONTROL = SimpleOption.ofBoolean("control.jump",
        ControlStuff.jumpControl,
        (value) -> ControlStuff.jumpControl = !ControlStuff.jumpControl);
    
    public static final SimpleOption<Boolean> SNEAK_CONTROL = SimpleOption.ofBoolean("control.sneak",
        ControlStuff.sneakControl,
        (value) -> ControlStuff.sneakControl = !ControlStuff.sneakControl);
    
    public static final SimpleOption<Boolean> WAVE_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.wave_chat_emote",
        TensorOptions.waveChatEmote,
        (value) -> settingsManager.setSetting("waveChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> SHRUG_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.shrug_chat_emote",
        TensorOptions.shrugChatEmote,
        (value) -> settingsManager.setSetting("shrugChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> TABLEFLIP_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.tableflip_chat_emote",
        TensorOptions.tableflipChatEmote,
        (value) -> settingsManager.setSetting("tableflipChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> TABLEBACK_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.tableback_chat_emote",
        TensorOptions.tablebackChatEmote,
        (value) -> settingsManager.setSetting("tablebackChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> GIMME_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.gimme_chat_emote",
        TensorOptions.gimmeChatEmote,
        (value) -> settingsManager.setSetting("gimmeChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> WIZARD_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.wizard_chat_emote",
        TensorOptions.wizardChatEmote,
        (value) -> settingsManager.setSetting("wizardChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> SKULL_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.skull_chat_emote",
        TensorOptions.skullChatEmote,
        (value) -> settingsManager.setSetting("skullChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> SUS_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.sus_chat_emote",
        TensorOptions.susChatEmote,
        (value) -> settingsManager.setSetting("susChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> HEART_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.heart_chat_emote",
        TensorOptions.heartChatEmote,
        (value) -> settingsManager.setSetting("heartChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> STAR_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.star_chat_emote",
        TensorOptions.starChatEmote,
        (value) -> settingsManager.setSetting("starChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> ARROW_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.arrow_chat_emote",
        TensorOptions.arrowChatEmote,
        (value) -> settingsManager.setSetting("arrowChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> JAVA_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.java_chat_emote",
        TensorOptions.javaChatEmote,
        (value) -> settingsManager.setSetting("javaChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> CONFUSED_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.confused_chat_emote",
        TensorOptions.confusedChatEmote,
        (value) -> settingsManager.setSetting("confusedChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> UNAMUSED_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.unamused_chat_emote",
        TensorOptions.unamusedChatEmote,
        (value) -> settingsManager.setSetting("unamusedChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> PRAY_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.pray_chat_emote",
        TensorOptions.prayChatEmote,
        (value) -> settingsManager.setSetting("prayChatEmote", value.toString()));
    
    public static final SimpleOption<Boolean> KIRBY_CHAT_EMOTE = SimpleOption.ofBoolean("chat_emotes.kirby_chat_emote",
        TensorOptions.kirbyChatEmote,
        (value) -> settingsManager.setSetting("kirbyChatEmote", value.toString()));
    
    public static final SimpleOption<Integer> KEYSTROKES_ANIMATION_TIME = new SimpleOption<>("keystrokes.keystrokes_animation_time",
        SimpleOption.emptyTooltip(),
        (optionText, value) -> Text.translatable("keystrokes.keystrokes_animation_time", TensorOptions.keystrokesAnimationTime),
        new SimpleOption.ValidatingIntSliderCallbacks(2, 20).withModifier(
            (sliderProgressValue) -> sliderProgressValue * 5,
            (value) -> value / 5
        ),
        TensorOptions.keystrokesAnimationTime,
        (value) -> settingsManager.setSetting("keystrokesAnimationTime", value.toString()));
    
    public static final SimpleOption<Integer> JUMP_COOLDOWN = new SimpleOption<>("miscellaneous.jump_cooldown",
        SimpleOption.emptyTooltip(),
        (optionText, value) -> Text.translatable("utility.item_use_cooldown", TensorOptions.jumpCooldown),
        new SimpleOption.ValidatingIntSliderCallbacks(0, 20).withModifier(
            (sliderProgressValue) -> sliderProgressValue,
            (value) -> value
        ),
        TensorOptions.jumpCooldown,
        (value) -> settingsManager.setSetting("jumpCooldown", value.toString()));
    
    public static final SimpleOption<Boolean> JUMP_COOLDOWN_OVERRIDE_TOGGLE = SimpleOption.ofBoolean("miscellaneous.jump_cooldown_override_toggle",
        TensorOptions.jumpCooldownOverrideToggle,
        (value) -> settingsManager.setSetting("jumpCooldownOverrideToggle", value.toString()));
    
    // coming soon!!!
    //
    //public static final SimpleOption<Boolean> NO_CHAT_SIGNING = SimpleOption.ofBoolean("utility.no_chat_signing",
    //    TensorOptions.noChatSigning,
    //    (value) -> settingsManager.setSetting("noChatSigning", value.toString()));
    
    public static final SimpleOption<Boolean> ZOOM_SCROLL = SimpleOption.ofBoolean("utility.zoom_scroll",
        TensorOptions.zoomScroll,
        (value) -> settingsManager.setSetting("zoomScroll", value.toString()));
}
