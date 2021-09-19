package tensor.gui;

import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;

public class DontClickOptionsScreen extends GameOptionsScreen
{
    public DontClickOptionsScreen(Screen parent, GameOptions gameOptions, Text title)
    {
        super(parent, gameOptions, title);
    }
    
    public void init()
    {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 - 12, 200, 20, new TranslatableText("dont_click.link"), (button) ->
            this.client.setScreen(new ConfirmChatLinkScreen((openInBrowser) ->
            {
                if(openInBrowser)
                {
                    Util.getOperatingSystem().open("https://bit.ly/IqT6zt");
                }
                this.client.setScreen(this);
            }, "https://bit.ly/IqT6zt", true))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, ScreenTexts.DONE, (button) ->
            this.client.setScreen(this.parent)));
    }
    
    public void render(MatrixStack matrices, int x, int y, float delta)
    {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, x, y, delta);
    }
}
