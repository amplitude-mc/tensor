package tensor.mixin.feature;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.option.TensorOptions;
import tensor.util.KeyBindManager;
import tensor.util.ZoomScroller;

@Mixin(Mouse.class)
public class ZoomScroll_Mouse
{
    @ModifyVariable
    (
        method = "onMouseScroll",
        at = @At("STORE"),
        ordinal = 2
    )
    private double getScroll(double scroll)
    {
        if(TensorOptions.zoomScroll && KeyBindManager.zooming())
        {
            ZoomScroller.addScroll(scroll);
            return 0;
        }
        return scroll;
    }
}
