package tensor.mixin.feature;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tensor.option.TensorOptions;
import tensor.util.KeyBindManager;
import tensor.util.ZoomScroller;

@Mixin(GameRenderer.class)
public class Zoom_GameRenderer
{
    @Inject
    (
        method = "getFov",
        at = @At("RETURN"),
        cancellable = true
    )
    private void zoom(CallbackInfoReturnable<Double> info)
    {
        if(KeyBindManager.zooming())
            info.setReturnValue(ZoomScroller.getZoom());
    }
}
