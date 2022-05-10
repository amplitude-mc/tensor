package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.util.ControlStuff;

@Mixin(MinecraftClient.class)
public class Control_MinecraftClient
{
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo info)
    {
        if(!Tensor.client.options.forwardKey.isPressed())
            Tensor.client.options.forwardKey.setPressed(ControlStuff.forwardControl);
        if(!Tensor.client.options.backKey.isPressed())
            Tensor.client.options.backKey.setPressed(ControlStuff.backwardControl);
        if(!Tensor.client.options.leftKey.isPressed())
            Tensor.client.options.leftKey.setPressed(ControlStuff.leftControl);
        if(!Tensor.client.options.rightKey.isPressed())
            Tensor.client.options.rightKey.setPressed(ControlStuff.rightControl);
        if(!Tensor.client.options.jumpKey.isPressed())
            Tensor.client.options.jumpKey.setPressed(ControlStuff.jumpControl);
        if(!Tensor.client.options.sneakKey.isPressed())
            Tensor.client.options.sneakKey.setPressed(ControlStuff.sneakControl);
    }
}
