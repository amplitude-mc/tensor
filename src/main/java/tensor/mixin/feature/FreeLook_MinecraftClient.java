package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.option.TensorOptions;

@Mixin(MinecraftClient.class)
public class FreeLook_MinecraftClient
{
    private static boolean freeLooking = false;
    
    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    private void freeLook(CallbackInfo info)
    {
        if(TensorOptions.freeLookKey.isPressed())
        {
            Tensor.client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
            freeLooking = true;
        }
        if(freeLooking && !TensorOptions.freeLookKey.isPressed())
        {
            Tensor.client.options.setPerspective(Perspective.FIRST_PERSON);
            freeLooking = false;
        }
    }
}
