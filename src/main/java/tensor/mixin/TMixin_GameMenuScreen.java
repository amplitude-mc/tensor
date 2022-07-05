package tensor.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tensor.Tensor;
import tensor.gui.TensorOptionsScreen;

@Mixin(GameMenuScreen.class)
public class TMixin_GameMenuScreen extends Screen
{
    protected TMixin_GameMenuScreen(Text title)
    {
        super(title);
    }
    
    @Redirect
    (
        method = "initWidgets",
        at = @At
        (
            value = "FIELD",
            target = "Lnet/minecraft/client/gui/screen/GameMenuScreen;height:I",
            opcode = Opcodes.GETFIELD
        ),
        slice = @Slice
        (
            from = @At
            (
                value = "INVOKE",
                target = "Lnet/minecraft/client/MinecraftClient;isIntegratedServerRunning()Z"
            )
        )
    )
    private int changeHeight(GameMenuScreen screen)
    {
        return screen.height + 96;
    }
    
    @Inject
    (
        method = "initWidgets",
        at = @At("RETURN")
    )
    private void addButton(CallbackInfo info)
    {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20, Text.translatable("title.tensor"), (button) ->
        {
            this.client.setScreen(new TensorOptionsScreen(this, Tensor.client.options));
        }));
    }
}
