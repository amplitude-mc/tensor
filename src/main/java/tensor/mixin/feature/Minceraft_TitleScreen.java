package tensor.mixin.feature;

import tensor.option.TensorOptions;
import net.minecraft.client.gui.screen.TitleScreen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class Minceraft_TitleScreen
{
    @Shadow
    private boolean isMinceraft;
    
    @Redirect
    (
        method = "render",
        at = @At
        (
            value = "FIELD",
            target = "Lnet/minecraft/client/gui/screen/TitleScreen;isMinceraft:Z",
            opcode = Opcodes.GETFIELD
        )
    )
    private boolean makeMincecraft(TitleScreen titleScreen)
    {
        if(TensorOptions.minceraft)
            return true;
        return this.isMinceraft;
    }
}
