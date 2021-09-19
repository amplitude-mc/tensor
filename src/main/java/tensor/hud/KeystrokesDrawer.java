package tensor.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import tensor.Tensor;
import tensor.option.TensorOptions;
import tensor.util.CPSCounter;

public class KeystrokesDrawer implements Drawer
{
    private static KeystrokesDrawer keystrokesDrawer;
    private final MinecraftClient client;
    
    private KeystrokesDrawer(MinecraftClient client)
    {
        this.client = client;
    }
    
    @Override
    public void draw()
    {
        this.drawWASD();
        this.drawMouse();
        this.drawSpace();
        this.drawShift();
        this.drawCPS();
    }
    
    private void drawWASD()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.wASD)
                return;
            int gapDist = 4 * this.client.getWindow().getWidth() / 2048;
            int wASDWidth = 32 * this.client.getWindow().getWidth() / 2048;
            int wASDHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int startX = 1200 * this.client.getWindow().getWidth() / 2048;
            int startY = 32 * this.client.getWindow().getWidth() / 2048;
            DrawableHelper.fill(matrixStack, startX + wASDWidth + gapDist, startY, startX + wASDWidth * 2 + gapDist, startY + wASDHeight, this.client.options.keyForward.isPressed() ? 0x64ffffff : 0x64000000);
            DrawableHelper.fill(matrixStack, startX, startY + wASDHeight + gapDist, startX + wASDWidth, startY + wASDHeight * 2 + gapDist, this.client.options.keyLeft.isPressed() ? 0x64ffffff : 0x64000000);
            DrawableHelper.fill(matrixStack, startX + wASDWidth + gapDist, startY + wASDHeight + gapDist, startX + wASDWidth * 2 + gapDist, startY + wASDHeight * 2 + gapDist, this.client.options.keyBack.isPressed() ? 0x64ffffff : 0x64000000);
            DrawableHelper.fill(matrixStack, startX + wASDWidth * 2 + gapDist * 2, startY + wASDHeight + gapDist, startX + wASDWidth * 3 + gapDist * 2, startY + wASDHeight * 2 + gapDist, this.client.options.keyRight.isPressed() ? 0x64ffffff : 0x64000000);
        });
    }
    
    private void drawMouse()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.mouse)
                return;
            int gapDist = 4 * this.client.getWindow().getWidth() / 2048;
            int wASDWidth = 32 * this.client.getWindow().getWidth() / 2048;
            int wASDHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int mouseWidth = (wASDWidth * 3 + gapDist) / 2;
            int mouseHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int startX = 1200 * this.client.getWindow().getWidth() / 2048;
            int startY = 32 * this.client.getWindow().getWidth() / 2048;
            DrawableHelper.fill(matrixStack, startX, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0), startX + mouseWidth, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + mouseHeight, this.client.options.keyAttack.isPressed() ? 0x64ffffff : 0x64000000);
            DrawableHelper.fill(matrixStack, startX + mouseWidth + gapDist, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0), startX + mouseWidth * 2 + gapDist, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + mouseHeight, this.client.options.keyUse.isPressed() ? 0x64ffffff : 0x64000000);
        });
    }
    
    private void drawSpace()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.space)
                return;
            int gapDist = 4 * this.client.getWindow().getWidth() / 2048;
            int wASDWidth = 32 * this.client.getWindow().getWidth() / 2048;
            int wASDHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int mouseHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int barWidth = wASDWidth * 3 + gapDist * 2;
            int barHeight = 16 * this.client.getWindow().getWidth() / 2048;
            int innerBarWidth = wASDWidth * 2;
            int innerBarHeight = 4 * this.client.getWindow().getWidth() / 2048;
            int startX = 1200 * this.client.getWindow().getWidth() / 2048;
            int startY = 32 * this.client.getWindow().getWidth() / 2048;
            DrawableHelper.fill(matrixStack, startX, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0), startX + barWidth, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + barHeight, this.client.options.keyJump.isPressed() ? 0x64ffffff : 0x64000000);
            DrawableHelper.fill(matrixStack, startX + (barWidth - innerBarWidth) / 2, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (barHeight - innerBarHeight) / 2, startX + barWidth - (barWidth - innerBarWidth) / 2, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + barHeight - (barHeight - innerBarHeight) / 2, this.client.options.keyJump.isPressed() ? 0x64000000 : 0x64ffffff);
        });
    }
    
    private void drawShift()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.shift)
                return;
            int gapDist = 4 * this.client.getWindow().getWidth() / 2048;
            int wASDWidth = 32 * this.client.getWindow().getWidth() / 2048;
            int wASDHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int mouseHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int barWidth = wASDWidth * 3 + gapDist * 2;
            int barHeight = 16 * this.client.getWindow().getWidth() / 2048;
            int startX = 1200 * this.client.getWindow().getWidth() / 2048;
            int startY = 32 * this.client.getWindow().getWidth() / 2048;
            DrawableHelper.fill(matrixStack, startX, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0), startX + barWidth, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + barHeight, this.client.options.keySneak.isPressed() ? 0x64ffffff : 0x64000000);
        });
    }
    
    private void drawCPS()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.cPS)
                return;
            int gapDist = 4 * this.client.getWindow().getWidth() / 2048;
            int wASDWidth = 32 * this.client.getWindow().getWidth() / 2048;
            int wASDHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int mouseHeight = 32 * this.client.getWindow().getWidth() / 2048;
            int barHeight = 16 * this.client.getWindow().getWidth() / 2048;
            int clickWidth = (wASDWidth * 3 + gapDist) / 2;
            int clickHeight = 4 * this.client.getWindow().getWidth() / 2048;
            int startX = 1200 * this.client.getWindow().getWidth() / 2048;
            int startY = 32 * this.client.getWindow().getWidth() / 2048;
            DrawableHelper.fill(matrixStack, startX, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + (TensorOptions.shift ? barHeight + gapDist : 0), startX + clickWidth, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + (TensorOptions.shift ? barHeight + gapDist : 0) + clickHeight * CPSCounter.leftCPS(),0x64ffffff);
            DrawableHelper.fill(matrixStack, startX + clickWidth + gapDist, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + (TensorOptions.shift ? barHeight + gapDist : 0), startX + clickWidth * 2 + gapDist, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + (TensorOptions.shift ? barHeight + gapDist : 0) + clickHeight * CPSCounter.rightCPS(),0x64ffffff);
        });
    }
    
    public static KeystrokesDrawer getInstance()
    {
        if(KeystrokesDrawer.keystrokesDrawer == null)
            KeystrokesDrawer.keystrokesDrawer = new KeystrokesDrawer(Tensor.client);
        return KeystrokesDrawer.keystrokesDrawer;
    }
}
