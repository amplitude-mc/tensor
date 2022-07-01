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
public class ItemUseCooldownOverrideToggle_MinecraftClient
{
    private static boolean iUCToggleHeld = false;
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void toggle(CallbackInfo info)
    {
        if(TensorOptions.itemUseCooldownOverrideKey.isPressed())
            ItemUseCooldownOverrideToggle_MinecraftClient.iUCToggleHeld = true;
        if(ItemUseCooldownOverrideToggle_MinecraftClient.iUCToggleHeld && !TensorOptions.itemUseCooldownOverrideKey.isPressed())
        {
            Tensor.settingsManager.setSetting("itemUseCooldownOverrideToggle", Boolean.valueOf(!TensorOptions.itemUseCooldownOverrideToggle).toString());
            Tensor.client.inGameHud.setOverlayMessage(Text.translatable("toggle.item_use_cooldown", (TensorOptions.itemUseCooldownOverrideToggle ? "§a" : "§c") + TensorOptions.itemUseCooldownOverrideToggle), false);
            ItemUseCooldownOverrideToggle_MinecraftClient.iUCToggleHeld = false;
        }
    }
}
