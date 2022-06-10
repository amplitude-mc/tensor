package tensor.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import tensor.Tensor;
import tensor.option.TensorOptions;

public class TextFieldsScreen extends GameOptionsScreen
{
    private TextFieldWidget youtubeAPIKey;
    private TextFieldWidget youtubeIDText;
    private TextFieldWidget subscribersText;
    
    public TextFieldsScreen(Screen parent, GameOptions gameOptions, Text title)
    {
        super(parent, gameOptions, title);
    }
    
    public void init()
    {
        this.youtubeAPIKey = new TextFieldWidget(this.textRenderer, this.width / 2, 35, 250, 20, this.youtubeAPIKey, Text.translatable("text.youtube_api_key"));
        this.youtubeAPIKey.setMaxLength(40);
        this.youtubeAPIKey.setText(TensorOptions.youtubeAPIKey);
        this.addSelectableChild(this.youtubeAPIKey);
        this.youtubeIDText = new TextFieldWidget(this.textRenderer, this.width / 2, 65, 250, 20, this.youtubeIDText, Text.translatable("text.youtube"));
        this.youtubeIDText.setMaxLength(40);
        this.youtubeIDText.setText(TensorOptions.youtubeID);
        this.addSelectableChild(this.youtubeIDText);
        this.subscribersText = new TextFieldWidget(this.textRenderer, this.width / 2, 95, 250, 20, this.subscribersText, Text.translatable("text.subs"));
        this.subscribersText.setMaxLength(40);
        this.subscribersText.setText(TensorOptions.subscribersText);
        this.addSelectableChild(this.subscribersText);
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 168, 200, 20, ScreenTexts.DONE, (button) ->
        {
            Tensor.settingsManager.setSetting("youtubeAPIKey", youtubeAPIKey.getText());
            Tensor.settingsManager.setSetting("youtubeID", youtubeIDText.getText());
            Tensor.settingsManager.setSetting("subscribersText", subscribersText.getText());
            this.client.setScreen(this.parent);
        }));
    }
    
    public void tick()
    {
        youtubeAPIKey.tick();
        youtubeIDText.tick();
        subscribersText.tick();
    }
    
    public void render(MatrixStack matrices, int x, int y, float delta)
    {
        this.renderBackground(matrices);
        this.youtubeAPIKey.render(matrices, x, y, delta);
        this.youtubeIDText.render(matrices, x, y, delta);
        this.subscribersText.render(matrices, x, y, delta);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("text.youtube_api_key"), this.width / 2 - 150, 40, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("text.youtube"), this.width / 2 - 150, 70, 16777215);
        drawCenteredText(matrices, this.textRenderer, Text.translatable("text.subs"), this.width / 2 - 150, 100, 16777215);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(matrices, x, y, delta);
    }
}
