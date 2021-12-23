package tensor.gui;

import tensor.option.TensorOptions;
import tensor.util.TControlsListWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;

public class KeybindsScreen extends GameOptionsScreen
{
    public KeyBinding focusedBinding;
    public long time;
    private TControlsListWidget keyBindingListWidget;
    private ButtonWidget resetButton;
    
    public KeybindsScreen(Screen parent, GameOptions gameOptions, Text title)
    {
        super(parent, gameOptions, title);
    }
    
    protected void init()
    {
        this.keyBindingListWidget = new TControlsListWidget(this, this.client);
        this.addSelectableChild(this.keyBindingListWidget);
        this.resetButton = this.addDrawableChild(new ButtonWidget(this.width / 2 - 155, this.height - 29, 150, 20, new TranslatableText("controls.resetAll"), (button) ->
        {
            KeyBinding[] keys = TensorOptions.allKeys;
            for(KeyBinding keyBinding : keys)
            {
                keyBinding.setBoundKey(keyBinding.getDefaultKey());
            }
            KeyBinding.updateKeysByCode();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 155 + 160, this.height - 29, 150, 20, ScreenTexts.DONE, (button) ->
            this.client.setScreen(this.parent)));
    }
    
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        if(this.focusedBinding != null)
        {
            this.gameOptions.setKeyCode(this.focusedBinding, InputUtil.Type.MOUSE.createFromCode(button));
            this.focusedBinding = null;
            KeyBinding.updateKeysByCode();
            return true;
        }
        else
        {
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if(this.focusedBinding != null)
        {
            if(keyCode == GLFW.GLFW_KEY_ESCAPE)
            {
                this.gameOptions.setKeyCode(this.focusedBinding, InputUtil.UNKNOWN_KEY);
                TensorOptions.setKeyCode(this.focusedBinding, InputUtil.UNKNOWN_KEY.getCode());
            }
            else
            {
                TensorOptions.setKeyCode(this.focusedBinding, keyCode);
                this.gameOptions.setKeyCode(this.focusedBinding, InputUtil.fromKeyCode(keyCode, scanCode));
            }
            this.focusedBinding = null;
            this.time = Util.getMeasuringTimeMs();
            KeyBinding.updateKeysByCode();
            return true;
        }
        else
        {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }
    
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta)
    {
        this.renderBackground(matrices);
        this.keyBindingListWidget.render(matrices, mouseX, mouseY, delta);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 16777215);
        boolean bl = false;
        KeyBinding[] var6 = TensorOptions.allKeys;
        for(KeyBinding keyBinding : var6)
        {
            if(!keyBinding.isDefault())
            {
                bl = true;
                break;
            }
        }
        this.resetButton.active = bl;
        super.render(matrices, mouseX, mouseY, delta);
    }
}
