package tensor.mixin.feature;

import tensor.option.TensorOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
            info.setReturnValue((double) TensorOptions.zoomFOV);
            client.options.smoothCameraEnabled = true;
            zoomed = true;
        }
        if(!TensorOptions.zoomKey.isPressed() && zoomed)
        {
            client.options.smoothCameraEnabled = originalSmooth;
            zoomed = false;
        }
        if(!TensorOptions.zoomKey.isPressed())
            originalSmooth = client.options.smoothCameraEnabled;
    }
}
