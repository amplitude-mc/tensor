package tensor.mixin.feature;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.util.EntityInterface;
import tensor.util.KeyBindManager;

@Mixin(Camera.class)
public abstract class FreeLook_Camera
{
    private boolean firstTime = true;
    
    @Shadow
    public abstract void setRotation(float yaw, float pitch);
    
    @Inject
    (
        method = "update",
        at = @At
        (
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V",
            shift = At.Shift.AFTER
        ),
        slice = @Slice
        (
            to = @At
            (
                value = "INVOKE",
                target = "Lnet/minecraft/client/render/Camera;setPos(DDD)V"
            )
        )
    )
    public void lockRotation(BlockView area, Entity cameraEntity, boolean thirdPerson, boolean frontFacing, float tickDelta, CallbackInfo info)
    {
        if(cameraEntity instanceof ClientPlayerEntity)
        {
            if(KeyBindManager.freeLooking())
            {
                EntityInterface entityIF = (EntityInterface) cameraEntity;
                if(this.firstTime && Tensor.client.player != null)
                {
                    entityIF.setCameraYaw(Tensor.client.player.getYaw());
                    entityIF.setCameraPitch(Tensor.client.player.getPitch());
                    this.firstTime = false;
                }
                this.setRotation(entityIF.getCameraYaw(), entityIF.getCameraPitch());
            } else
            {
                this.firstTime = true;
            }
        }
    }
}
