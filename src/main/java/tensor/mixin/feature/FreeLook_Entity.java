package tensor.mixin.feature;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.util.EntityInterface;
import tensor.util.KeyBindManager;

@Mixin(Entity.class)
public class FreeLook_Entity implements EntityInterface
{
    @Unique
    private float cameraPitch;
    @Unique
    private float cameraYaw;
    
    @Inject
    (
        method = "changeLookDirection",
        at = @At("HEAD"),
        cancellable = true
    )
    public void changeCameraLookDirection(double xDelta, double yDelta, CallbackInfo info)
    {
        if(!KeyBindManager.freeLooking() || !((Object) this instanceof ClientPlayerEntity))
            return;
        double pitchDelta = (yDelta * 0.15);
        double yawDelta = (xDelta * 0.15);
        this.cameraPitch = MathHelper.clamp(this.cameraPitch + (float) pitchDelta, -90.0F, 90.0F);
        this.cameraYaw += (float) yawDelta;
        info.cancel();
    }
    
    @Override
    @Unique
    public float getCameraPitch()
    {
        return this.cameraPitch;
    }
    
    @Override
    @Unique
    public float getCameraYaw()
    {
        return this.cameraYaw;
    }
    
    @Override
    @Unique
    public void setCameraPitch(float pitch)
    {
        this.cameraPitch = pitch;
    }
    
    @Override
    @Unique
    public void setCameraYaw(float yaw)
    {
        this.cameraYaw = yaw;
    }
}
