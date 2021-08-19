package tensor.mixin;

import tensor.Tensor;
import tensor.gui.TensorOptionsScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TMixin_TitleScreen extends Screen
{
    protected TMixin_TitleScreen(Text title)
    {
        super(title);
    }
    
    @Inject(method = "initWidgetsNormal", at = @At("RETURN"))
    private void addButton(CallbackInfo info)
    {
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 104 + 25, this.height / 4 + 48 + 72 + 12, 100, 20, new TranslatableText("title.tensor"), (button) ->
        {
            this.client.setScreen(new TensorOptionsScreen(this, Tensor.client.options));
        }));
    }
}
