package tensor.mixin.feature;

import tensor.option.TensorOptions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class NoPortalGuiClosing_ClientPlayerEntity
{
    @Redirect(method = "updateNausea", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;shouldPause()Z"))
    private boolean onDoesGuiPauseGame(Screen gui)
    {
        if(TensorOptions.noPortalGUIClosing)
        {
            return true;
        }
        return gui.shouldPause();
    }
}
