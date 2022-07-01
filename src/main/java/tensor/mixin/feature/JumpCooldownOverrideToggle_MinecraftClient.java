package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.option.TensorOptions;

@Mixin(MinecraftClient.class)
public class JumpCooldownOverrideToggle_MinecraftClient
{
    private static boolean jumpToggleHeld = false;
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void toggle(CallbackInfo info)
    {
        if(TensorOptions.jumpCooldownOverrideKey.isPressed())
            JumpCooldownOverrideToggle_MinecraftClient.jumpToggleHeld = true;
        if(JumpCooldownOverrideToggle_MinecraftClient.jumpToggleHeld && !TensorOptions.jumpCooldownOverrideKey.isPressed())
        {
            Tensor.settingsManager.setSetting("jumpCooldownOverrideToggle", Boolean.valueOf(!TensorOptions.jumpCooldownOverrideToggle).toString());
            Tensor.client.inGameHud.setOverlayMessage(Text.translatable("toggle.jump_cooldown", (TensorOptions.jumpCooldownOverrideToggle ? "§a" : "§c") + TensorOptions.jumpCooldownOverrideToggle), false);
            JumpCooldownOverrideToggle_MinecraftClient.jumpToggleHeld = false;
        }
    }
}
