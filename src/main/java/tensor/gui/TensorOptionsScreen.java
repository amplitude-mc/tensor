package tensor.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class TensorOptionsScreen extends GameOptionsScreen
{
    public TensorOptionsScreen(Screen parent, GameOptions gameOptions)
    {
        super(parent, gameOptions, Text.translatable("title.tensor"));
    }
    
    protected void init()
    {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 0 % 2 * 160, this.height / 6 - 12 + 24 * (0 >> 1), 150, 20, Text.translatable("options.visuals"), (button) ->
            this.client.setScreen(new VisualsOptionsScreen(this, this.gameOptions, Text.translatable("options.visuals")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 1 % 2 * 160, this.height / 6 - 12 + 24 * (1 >> 1), 150, 20, Text.translatable("options.chat_emotes"), (button) ->
            this.client.setScreen(new ChatEmotesOptionsScreen(this, this.gameOptions, Text.translatable("options.chat_emotes")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 2 % 2 * 160, this.height / 6 - 12 + 24 * (2 >> 1), 150, 20, Text.translatable("options.fixes"), (button) ->
            this.client.setScreen(new FixesOptionsScreen(this, this.gameOptions, Text.translatable("options.fixes")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 3 % 2 * 160, this.height / 6 - 12 + 24 * (3 >> 1), 150, 20, Text.translatable("options.utility"), (button) ->
            this.client.setScreen(new UtilityOptionsScreen(this, this.gameOptions, Text.translatable("options.utility")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 4 % 2 * 160, this.height / 6 - 12 + 24 * (4 >> 1), 150, 20, Text.translatable("options.keystrokes"), (button) ->
            this.client.setScreen(new KeystrokesOptionsScreen(this, this.gameOptions, Text.translatable("options.keystrokes")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 5 % 2 * 160, this.height / 6 - 12 + 24 * (5 >> 1), 150, 20, Text.translatable("options.text"), (button) ->
            this.client.setScreen(new TextFieldsScreen(this, this.gameOptions, Text.translatable("options.text")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 6 % 2 * 160, this.height / 6 - 12 + 24 * (6 >> 1), 150, 20, Text.translatable("options.control"), (button) ->
            this.client.setScreen(new ControlOptionsScreen(this, this.gameOptions, Text.translatable("options.control")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 7 % 2 * 160, this.height / 6 - 12 + 24 * (7 >> 1), 150, 20, Text.translatable("options.optimization"), (button) ->
            this.client.setScreen(new OptimizationOptionsScreen(this, this.gameOptions, Text.translatable("options.optimization")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 8 % 2 * 160, this.height / 6 - 12 + 24 * (8 >> 1), 150, 20, Text.translatable("options.dont_click"), (button) ->
            this.client.setScreen(new DontClickOptionsScreen(this, this.gameOptions, Text.translatable("options.dont_click")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 9 % 2 * 160, this.height / 6 - 12 + 24 * (9 >> 1), 150, 20, Text.translatable("options.keybinds"), (button) ->
            this.client.setScreen(new KeybindsScreen(this, this.gameOptions, Text.translatable("options.keybinds")))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 10 % 2 * 160, this.height / 6 - 12 + 24 * (10 >> 1), 150, 20, Text.translatable("options.miscellaneous"), (button) ->
            this.client.setScreen(new MiscellaneousOptionsScreen(this, this.gameOptions, Text.translatable("options.miscellaneous")))));
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
