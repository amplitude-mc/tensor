package tensor.mixin.feature;

import tensor.option.TensorOptions;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Mouse.class)
public class MacScrollingFix_Mouse
{
    @ModifyVariable
    (
        method = "onMouseScroll",
        at = @At("HEAD"),
        index = 5
    )
    private double scrollFix(double vertical1, long window, double horizontal, double vertical)
    {
        if(TensorOptions.macScrollingFix)
            return vertical1 == 0 ? horizontal : vertical1;
        return vertical1;
    }
}
