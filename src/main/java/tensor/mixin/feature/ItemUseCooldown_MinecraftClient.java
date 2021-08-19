package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.option.TensorOptions;

@Mixin(MinecraftClient.class)
public class ItemUseCooldown_MinecraftClient
{
    @Shadow
    private int itemUseCooldown;
    
    private static boolean toggleHeld = false;
    
    @Redirect(method = "doItemUse", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;itemUseCooldown:I", opcode = Opcodes.PUTFIELD))
    private void changeItemUse(MinecraftClient client, int cooldown)
    {
        if(TensorOptions.itemUseCooldownOverrideToggle)
            itemUseCooldown = (int) TensorOptions.itemUseCooldown;
        else
            itemUseCooldown = 4;
    }
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void toggle(CallbackInfo info)
    {
        if(TensorOptions.itemUseCooldownOverrideKey.isPressed())
            toggleHeld = true;
        if(toggleHeld && !TensorOptions.itemUseCooldownOverrideKey.isPressed())
        {
            Tensor.settingsManager.setSetting("itemUseCooldownOverrideToggle", Boolean.valueOf(!TensorOptions.itemUseCooldownOverrideToggle).toString());
            Tensor.client.inGameHud.setOverlayMessage(new TranslatableText("toggle.item_use_cooldown", TensorOptions.itemUseCooldownOverrideToggle), false);
            toggleHeld = false;
        }
    }
}
