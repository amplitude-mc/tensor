package tensor.mixin.optimization;

import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tensor.option.TensorOptions;
import tensor.util.math.DevmasterMath;

@Mixin(Quaternion.class)
public class Optimization_Quaternion
{
    @Inject
    (
        method = "sin",
        at = @At("RETURN"),
        cancellable = true
    )
    private static void sin(float x, CallbackInfoReturnable<Float> info)
    {
        if(TensorOptions.fastTrig)
            info.setReturnValue(DevmasterMath.sin(x));
    }
    
    @Inject
    (
        method = "cos",
        at = @At("RETURN"),
        cancellable = true
    )
    private static void cos(float x, CallbackInfoReturnable<Float> info)
    {
        if(TensorOptions.fastTrig)
            info.setReturnValue(DevmasterMath.cos(x));
    }
}
