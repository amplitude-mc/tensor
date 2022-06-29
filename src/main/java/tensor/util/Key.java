package tensor.util;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import tensor.Tensor;
import tensor.option.TensorOptions;

public class Key
{
    private final KeyBinding key;
    private final Rectangle rectangle;
    private final Color backgroundColor;
    private final Color pressedBackgroundColor;
    private final Color textColor;
    private final Color pressedTextColor;
    private final KeystrokeRenderer renderer;
    private float startAnimation = -1;
    private boolean wasPressed = false;
    
    public Key(KeyBinding key, Rectangle rectangle, Color backgroundColor, Color pressedBackgroundColor, Color textColor, Color pressedTextColor, KeystrokeRenderer renderer)
    {
        this.key = key;
        this.rectangle = rectangle;
        this.backgroundColor = backgroundColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
        this.textColor = textColor;
        this.pressedTextColor = pressedTextColor;
        this.renderer = renderer;
    }
    
    public void render(MatrixStack matrixStack, Coordinate offset)
    {
        if(key.isPressed() != this.wasPressed)
            this.startAnimation = Util.getMeasuringTimeMs();
        DrawStuff.fillRect(matrixStack, this.rectangle.offset(offset), getColor());
        if(Util.getMeasuringTimeMs() - this.startAnimation >= TensorOptions.animationTime)
            this.startAnimation = -1;
        this.renderer.render(this.withOffset(offset), matrixStack);
        this.wasPressed = this.key.isPressed();
    }
    
    public Key withOffset(Coordinate offset)
    {
        return new Key(this.key, this.rectangle.offset(offset), this.backgroundColor, this.pressedBackgroundColor, this.textColor, this.pressedTextColor, this.renderer);
    }
    
    public Rectangle getRectangle()
    {
        return rectangle;
    }
    
    public Color getColor()
    {
        if(this.key.isPressed())
            return this.backgroundColor.blend(this.pressedBackgroundColor, this.animationProgress());
        else
            return this.pressedBackgroundColor.blend(this.backgroundColor, this.animationProgress());
    }
    
    public Color getTextColor()
    {
        if(this.key.isPressed())
            return this.textColor.blend(this.pressedTextColor, this.animationProgress());
        else
            return this.pressedTextColor.blend(this.textColor, this.animationProgress());
    }
    
    public float animationProgress()
    {
        return this.startAnimation == -1 ? 1 : MathHelper.clamp((Util.getMeasuringTimeMs() - this.startAnimation) / TensorOptions.animationTime, 0, 1);
    }
    
    public static Key create(KeyBinding keystroke, Rectangle rectangle)
    {
        return new Key(keystroke, rectangle, new Color(0x64000000), new Color(0x64ffffff), new Color(0xffffffff), new Color(0xff000000),
            (key, matrixStack) ->
            {
                String name;
                if(keystroke.getTranslationKey().equalsIgnoreCase(Tensor.client.options.attackKey.getTranslationKey()))
                    name = "LMB";
                else if(keystroke.getTranslationKey().equalsIgnoreCase(Tensor.client.options.useKey.getTranslationKey()))
                    name = "RMB";
                else if(keystroke.getTranslationKey().equalsIgnoreCase(Tensor.client.options.jumpKey.getTranslationKey()))
                    name = "";
                else if(keystroke.getTranslationKey().equalsIgnoreCase(Tensor.client.options.sneakKey.getTranslationKey()))
                    name = "SHIFT";
                else
                    name = keystroke.getBoundKeyLocalizedText().getString().toUpperCase();
                Rectangle rect = key.getRectangle();
                float x = rect.getX() + (float) rect.getWidth() / 2 - (float) Tensor.client.textRenderer.getWidth(name) / 2;
                float y = rect.getY() + (float) rect.getHeight() / 2 - 4;
                DrawStuff.drawString(matrixStack, Tensor.client.textRenderer, name, x, y, key.getTextColor().getColor(), false);
            });
    }
}
