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
        if(!Tensor.client.options.keyForward.isPressed())
            Tensor.client.options.keyForward.setPressed(ControlStuff.forwardControl);
        if(!Tensor.client.options.keyBack.isPressed())
            Tensor.client.options.keyBack.setPressed(ControlStuff.backwardControl);
        if(!Tensor.client.options.keyLeft.isPressed())
            Tensor.client.options.keyLeft.setPressed(ControlStuff.leftControl);
        if(!Tensor.client.options.keyRight.isPressed())
            Tensor.client.options.keyRight.setPressed(ControlStuff.rightControl);
        if(!Tensor.client.options.keyJump.isPressed())
            Tensor.client.options.keyJump.setPressed(ControlStuff.jumpControl);
        if(!Tensor.client.options.keySneak.isPressed())
            Tensor.client.options.keySneak.setPressed(ControlStuff.sneakControl);
    }
}
