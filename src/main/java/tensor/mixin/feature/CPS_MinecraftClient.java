package tensor.mixin.feature;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.util.CPSCounter;

@Mixin(MinecraftClient.class)
public class CPS_MinecraftClient
{
    @Inject
    (
        method = "tick",
        at = @At("HEAD")
    )
    private void onTick(CallbackInfo info)
    {
        CPSCounter.tick();
    }
}
