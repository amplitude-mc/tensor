package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tensor.option.TensorOptions;
import tensor.util.ZoomScroller;

@Mixin(GameRenderer.class)
public class Zoom_GameRenderer
{
    @Shadow
    private MinecraftClient client;
    private boolean zoomed;
    private boolean originalSmooth = MinecraftClient.getInstance().options.smoothCameraEnabled;
    
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void zoom(CallbackInfoReturnable<Double> info)
    {
        if(TensorOptions.zoomKey.isPressed())
        {
            info.setReturnValue(ZoomScroller.getZoom());
            this.client.options.smoothCameraEnabled = true;
            this.zoomed = true;
        }
        if(!TensorOptions.zoomKey.isPressed() && this.zoomed)
        {
            this.client.options.smoothCameraEnabled = this.originalSmooth;
            this.zoomed = false;
            ZoomScroller.resetScroll();
        }
        if(!TensorOptions.zoomKey.isPressed())
            this.originalSmooth = this.client.options.smoothCameraEnabled;
    }
}
