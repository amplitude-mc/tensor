package tensor.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import tensor.Tensor;
import tensor.option.TOptions;
import tensor.option.TensorOptions;

public class VisualsOptionsScreen extends GameOptionsScreen
{
    private static final Option[] OPTIONS;
    private ButtonListWidget list;
    
    public VisualsOptionsScreen(Screen parent, GameOptions gameOptions, Text title)
    {
        super(parent, gameOptions, title);
    }
    
    public void init()
    {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addAll(OPTIONS);
        this.addSelectableChild(this.list);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) ->
        {
            this.client.setScreen(this.parent);
        }));
    }
    
    public void render(MatrixStack matrices, int x, int y, float delta)
    {
        this.renderBackground(matrices);
        this.list.render(matrices, x, y, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, x, y, delta);
    }
    
    static
    {
        OPTIONS = new Option[] {TOptions.MINCERAFT, TOptions.YOUTUBE_SUB_COUNT, TOptions.SPRINTING_TEXT};
    }
}
