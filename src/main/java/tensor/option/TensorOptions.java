package tensor.option;

import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import tensor.Tensor;

import java.lang.reflect.Field;

import static tensor.option.Categories.*;

public class TensorOptions
{
    @Setting(VISUALS)
    public static boolean minceraft = false;
    
    @Setting(FIXES)
    public static boolean macScrollingFix = false;
    
    @Setting(KEYBINDS)
    @Key("keybinds.zoom_key")
    public static int zoom = GLFW.GLFW_KEY_C;
    public static KeyBinding zoomKey = new KeyBinding("keybinds.zoom_key", zoom, "key.category.utility");
    
    @Setting(UTILITY)
    public static boolean permanentSprint = false;
    
    @Setting(UTILITY)
    public static int zoomFOV = 30;
    
    @Setting
    public static boolean noPortalGUIClosing = false;
    
    @Setting(KEYBINDS)
    @Key("keybinds.open_gui_key")
    public static int openGUI = GLFW.GLFW_KEY_RIGHT_SHIFT;
    public static KeyBinding openGUIKey = new KeyBinding("keybinds.open_gui_key", openGUI, "key.category.miscellaneous");
    
    @Setting(KEYBINDS)
    @Key("keybinds.free_look_key")
    public static int freeLook = GLFW.GLFW_KEY_LEFT_ALT;
    public static KeyBinding freeLookKey = new KeyBinding("keybinds.free_look_key", freeLook, "key.category.utility");
    
    @Setting(UTILITY)
    public static int itemUseCooldown = 4;
    
    @Setting(KEYBINDS)
    @Key("keybinds.item_use_cooldown_override_key")
    public static int itemUseCooldownOverride = GLFW.GLFW_KEY_U;
    public static KeyBinding itemUseCooldownOverrideKey = new KeyBinding("keybinds.item_use_cooldown_override_key", itemUseCooldownOverride, "key.category.toggles");
    
    @Setting(UTILITY)
    public static boolean itemUseCooldownOverrideToggle = false;
    
    @Setting(TEXTFIELDS)
    public static String youtubeID = "";
    
    @Setting(VISUALS)
    public static boolean subCounter = false;
    
    @Setting(TEXTFIELDS)
    public static String youtubeAPIKey = "";
    
    @Setting(VISUALS)
    public static boolean sprintingText = false;
    
    @Setting(TEXTFIELDS)
    public static String subscribersText = "Subscriber count: ";
    
    @Setting(KEYSTROKES)
    public static boolean wASD = false;
    
    @Setting(KEYSTROKES)
    public static boolean mouse = false;
    
    @Setting(KEYSTROKES)
    public static boolean space = false;
    
    @Setting(KEYSTROKES)
    public static boolean shift = false;
    
    @Setting(KEYSTROKES)
    public static boolean cPS = false;
    
    @Setting(KEYSTROKES)
    public static boolean cPSText = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean waveChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean shrugChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean tableflipChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean tablebackChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean gimmeChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean wizardChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean skullChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean susChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean heartChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean starChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean arrowChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean javaChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean confusedChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean unamusedChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean prayChatEmote = false;
    
    @Setting(CHAT_EMOTES)
    public static boolean kirbyChatEmote = false;
    
    public static KeyBinding[] allKeys = new KeyBinding[] {zoomKey, openGUIKey, freeLookKey, itemUseCooldownOverrideKey};
    
    public static void setKeyCode(KeyBinding key, int keyCode)
    {
        for(Field field : TensorOptions.class.getFields())
        {
            Key fieldAnnotation = field.getAnnotation(Key.class);
            if(fieldAnnotation == null)
                continue;
            try
            {
                String keyFor = fieldAnnotation.value();
                if(keyFor.equals(key.getTranslationKey()))
                {
                    field.set(null, keyCode);
                    Tensor.settingsManager.setSetting(field.getName(), String.valueOf(keyCode));
                }
            }
            catch(IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }
}
