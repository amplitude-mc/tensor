package tensor.mixin.feature;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.util.CPSCounter;

@Mixin(Mouse.class)
public class CPS_Mouse
{
    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void clicked(long window, int button, int action, int mods, CallbackInfo info)
    {
        if(action == 1)
            CPSCounter.click(button);
    }
}
