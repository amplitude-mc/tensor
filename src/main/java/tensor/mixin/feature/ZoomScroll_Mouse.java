package tensor.mixin.feature;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.option.TensorOptions;
import tensor.util.ZoomScroller;

@Mixin(Mouse.class)
public class ZoomScroll_Mouse
{
    @Inject(method = "onMouseScroll", at = @At("HEAD"))
    private double getScroll(long window, double horizontal, double vertical, CallbackInfo info)
    {
        if(vertical == 0)
            vertical = horizontal;
        double scroll = (Tensor.client.options.getDiscreteMouseScroll().getValue() ? Math.signum(vertical) : vertical) * Tensor.client.options.getMouseWheelSensitivity().getValue();
        if(TensorOptions.zoomScroll)
            ZoomScroller.addScroll(scroll);
        return scroll;
    }
}
