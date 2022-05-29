package tensor.mixin.feature;

import tensor.option.TensorOptions;
import tensor.util.EntityInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class FreeLook_Camera
{
    private boolean firstTime = true;
    
    @Shadow
    public abstract void setRotation(float yaw, float pitch);
    
    @Inject(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V", ordinal = 0, shift = At.Shift.AFTER))
    public void lockRotation(BlockView focusedBlock, Entity cameraEntity, boolean isThirdPerson, boolean isFrontFacing, float f, CallbackInfo ci)
    {
        if(TensorOptions.freeLookKey.isPressed() && cameraEntity instanceof ClientPlayerEntity)
        {
            EntityInterface entityIF = (EntityInterface) cameraEntity;
            if(firstTime && MinecraftClient.getInstance().player != null)
            {
                entityIF.setCameraYaw(MinecraftClient.getInstance().player.getYaw());
                entityIF.setCameraPitch(MinecraftClient.getInstance().player.getPitch());
                firstTime = false;
            }
            this.setRotation(entityIF.getCameraYaw(), entityIF.getCameraPitch());
        }
        if(!TensorOptions.freeLookKey.isPressed() && cameraEntity instanceof ClientPlayerEntity)
        {
            firstTime = true;
        }
    }
}
