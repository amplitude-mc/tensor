package tensor.mixin.feature;

import tensor.option.TensorOptions;
import tensor.gui.TensorOptionsScreen;
import net.minecraft.client.MinecraftClient;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public class OpenGUI_MinecraftClient
{
    @Redirect(method = "run", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;running:Z", opcode = Opcodes.GETFIELD))
    private boolean openGUI(MinecraftClient client)
    {
        if(client.isRunning())
            if(TensorOptions.openGUIKey.isPressed())
                client.setScreen(new TensorOptionsScreen(null, client.options));
        return client.isRunning();
    }
}
