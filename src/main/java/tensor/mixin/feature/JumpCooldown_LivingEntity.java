package tensor.mixin.feature;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.option.TensorOptions;

@Mixin(LivingEntity.class)
public class JumpCooldown_LivingEntity
{
    @Shadow
    private int jumpingCooldown;
    private boolean firstTime = true;
    
    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void changeJumping(CallbackInfo info)
    {
        if(this.jumpingCooldown == 10 && this.firstTime && TensorOptions.jumpCooldownOverrideToggle)
        {
            this.jumpingCooldown = TensorOptions.jumpCooldown;
            this.firstTime = false;
        } else if(this.jumpingCooldown < 10 && !this.firstTime && TensorOptions.jumpCooldownOverrideToggle)
        {
            this.firstTime = true;
        }
    }
}
