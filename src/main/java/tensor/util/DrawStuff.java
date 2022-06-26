package tensor.util;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class DrawStuff
{
    public static void fillRect(MatrixStack matrixStack, Rectangle rect, Color color)
    {
        DrawStuff.fillRect(matrixStack, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight(), color.getColor());
    }
    
    public static void fillRect(MatrixStack matrixStack, int x, int y, int width, int height, int color)
    {
        DrawableHelper.fill(matrixStack, x, y, x + width, y + height, color);
    }
    
    public static void drawString(MatrixStack matrixStack, TextRenderer textRenderer, String text, float x, float y, int color, boolean shadow)
    {
        if(shadow)
            textRenderer.drawWithShadow(matrixStack, text, x, y, color);
        else
            textRenderer.draw(matrixStack, text, x, y, color);
    }
}
