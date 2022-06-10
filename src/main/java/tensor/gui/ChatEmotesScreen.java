package tensor.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import tensor.option.TOptions;

public class ChatEmotesScreen extends GameOptionsScreen
{
    private static final SimpleOption<?>[] OPTIONS;
    private ButtonListWidget list;
    
    public ChatEmotesScreen(Screen parent, GameOptions gameOptions, Text title)
    {
        super(parent, gameOptions, title);
    }
    
    public void init()
    {
        this.list = new ButtonListWidget(this.client, this.width, this.height, 32, this.height - 32, 25);
        this.list.addAll(OPTIONS);
        this.addSelectableChild(this.list);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, (button) ->
            this.client.setScreen(this.parent)));
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
        OPTIONS = new SimpleOption<?>[] {TOptions.WAVE_CHAT_EMOTE, TOptions.SHRUG_CHAT_EMOTE, TOptions.TABLEFLIP_CHAT_EMOTE, TOptions.TABLEBACK_CHAT_EMOTE, TOptions.GIMME_CHAT_EMOTE, TOptions.WIZARD_CHAT_EMOTE, TOptions.SKULL_CHAT_EMOTE, TOptions.SUS_CHAT_EMOTE, TOptions.HEART_CHAT_EMOTE, TOptions.STAR_CHAT_EMOTE, TOptions.ARROW_CHAT_EMOTE, TOptions.JAVA_CHAT_EMOTE};
    }
}
