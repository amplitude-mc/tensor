package tensor.mixin.feature;

import net.minecraft.network.encryption.PlayerPublicKey;
import tensor.option.TensorOptions;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public abstract class ToggleSprint_ClientPlayerEntity extends AbstractClientPlayerEntity
{
    @Shadow
    public Input input;
    
    public ToggleSprint_ClientPlayerEntity(ClientWorld world, GameProfile profile, PlayerPublicKey playerPublicKey)
    {
        super(world, profile, playerPublicKey);
    }
    
    @Inject(method = "tickMovement", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerAbilities;allowFlying:Z", ordinal = 1))
    private void overrideSprint(CallbackInfo info)
    {
        if(TensorOptions.permanentSprint && !this.isSprinting() && !this.isUsingItem() && this.input.movementForward >= 0.8F && (this.getHungerManager().getFoodLevel() > 6.0F || this.getAbilities().allowFlying) && !this.hasStatusEffect(StatusEffects.BLINDNESS))
        {
            this.setSprinting(true);
        }
    }
}
