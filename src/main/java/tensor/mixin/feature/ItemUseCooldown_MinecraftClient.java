package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tensor.option.TensorOptions;

@Mixin(MinecraftClient.class)
public class ItemUseCooldown_MinecraftClient
{
    @Shadow
    private int itemUseCooldown;
    
    @Redirect
    (
        method = "doItemUse",
        at = @At
        (
            value = "FIELD",
            target = "Lnet/minecraft/client/MinecraftClient;itemUseCooldown:I",
            opcode = Opcodes.PUTFIELD
        )
    )
    private void changeItemUse(MinecraftClient client, int cooldown)
    {
        if(TensorOptions.itemUseCooldownOverrideToggle)
            this.itemUseCooldown = TensorOptions.itemUseCooldown;
        else
            this.itemUseCooldown = 4;
    }
}
