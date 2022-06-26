package tensor.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import tensor.Tensor;
import tensor.option.TensorOptions;
import tensor.util.*;

public class KeystrokesDrawer implements Drawer
{
    private static KeystrokesDrawer keystrokesDrawer;
    private final MinecraftClient client;
    private Key W;
    private Key A;
    private Key S;
    private Key D;
    private Key LMB;
    private Key RMB;
    private Key space;
    private Key shift;
    
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
        int gapDist = 4;
        int wASDWidth = 32;
        int wASDHeight = 32;
        int startX = 1200;
        int startY = 32;
        this.W = Key.create(this.client.options.forwardKey, new Rectangle(new Coordinate(startX + gapDist + wASDWidth, startY), wASDWidth, wASDHeight));
        this.A = Key.create(this.client.options.leftKey, new Rectangle(new Coordinate(startX, startY + gapDist + wASDHeight), wASDWidth, wASDHeight));
        this.S = Key.create(this.client.options.backKey, new Rectangle(new Coordinate(startX + gapDist + wASDWidth, startY + gapDist + wASDHeight), wASDWidth, wASDHeight));
        this.D = Key.create(this.client.options.rightKey, new Rectangle(new Coordinate(startX + gapDist * 2 + wASDWidth * 2, startY + gapDist + wASDHeight), wASDWidth, wASDHeight));
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.wASD)
                return;
            matrixStack.push();
            matrixStack.scale((float) this.client.getWindow().getWidth() / 2048, (float) this.client.getWindow().getWidth() / 2048, 1);
            this.W.render(matrixStack, Coordinate.ZERO);
            this.A.render(matrixStack, Coordinate.ZERO);
            this.S.render(matrixStack, Coordinate.ZERO);
            this.D.render(matrixStack, Coordinate.ZERO);
            matrixStack.pop();
        });
    }
    
    private void drawMouse()
    {
        int gapDist = 4;
        int wASDWidth = 32;
        int wASDHeight = 32;
        int mouseWidth = (wASDWidth * 3 + gapDist) / 2;
        int mouseHeight = 32;
        int startX = 1200;
        int startY = 32;
        this.LMB = Key.create(this.client.options.attackKey, new Rectangle(new Coordinate(startX, startY), mouseWidth, mouseHeight));
        this.RMB = Key.create(this.client.options.useKey, new Rectangle(new Coordinate(startX + gapDist + mouseWidth, startY), mouseWidth, mouseHeight));
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.mouse)
                return;
            matrixStack.push();
            matrixStack.scale((float) this.client.getWindow().getWidth() / 2048, (float) this.client.getWindow().getWidth() / 2048, 1);
            this.LMB.render(matrixStack, new Coordinate(0, TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0));
            this.RMB.render(matrixStack, new Coordinate(0, TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0));
            matrixStack.pop();
        });
    }
    
    private void drawSpace()
    {
        int gapDist = 4;
        int wASDWidth = 32;
        int wASDHeight = 32;
        int mouseHeight = 32;
        int barWidth = wASDWidth * 3 + gapDist * 2;
        int barHeight = 16;
        int innerBarWidth = wASDWidth * 3 / 2;
        int innerBarHeight = 2;
        int startX = 1200;
        int startY = 32;
        Rectangle innerBar = new Rectangle(startX + (barWidth - innerBarWidth) / 2, startY + (barHeight - innerBarHeight) / 2, innerBarWidth, innerBarHeight);
        this.space = new Key(this.client.options.jumpKey, new Rectangle(new Coordinate(startX, startY), barWidth, barHeight), new Color(0x64000000), new Color(0x64ffffff), new Color(0xffffffff), new Color(0xff000000),
            (key, matrixStack) -> DrawStuff.fillRect(matrixStack, innerBar.offset(new Coordinate(0, (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0))), key.getTextColor()));
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.space)
                return;
            matrixStack.push();
            matrixStack.scale((float) this.client.getWindow().getWidth() / 2048, (float) this.client.getWindow().getWidth() / 2048, 1);
            this.space.render(matrixStack, new Coordinate(0, (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0)));
            matrixStack.pop();
        });
    }
    
    private void drawShift()
    {
        int gapDist = 4;
        int wASDWidth = 32;
        int wASDHeight = 32;
        int mouseHeight = 32;
        int barWidth = wASDWidth * 3 + gapDist * 2;
        int barHeight = 16;
        int startX = 1200;
        int startY = 32;
        this.shift = Key.create(this.client.options.sneakKey, new Rectangle(new Coordinate(startX, startY), barWidth, barHeight));
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.shift)
                return;
            matrixStack.push();
            matrixStack.scale((float) this.client.getWindow().getWidth() / 2048, (float) this.client.getWindow().getWidth() / 2048, 1);
            shift.render(matrixStack, new Coordinate(0, (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0)));
            matrixStack.pop();
        });
    }
    
    private void drawCPS()
    {
        int gapDist = 4;
        int wASDWidth = 32;
        int wASDHeight = 32;
        int mouseHeight = 32;
        int barHeight = 16;
        int clickWidth = (wASDWidth * 3 + gapDist) / 2;
        int clickHeight = 4;
        int startX = 1200;
        int startY = 32;
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.cPS)
                return;
            matrixStack.push();
            matrixStack.scale((float) this.client.getWindow().getWidth() / 2048, (float) this.client.getWindow().getWidth() / 2048, 1);
            DrawStuff.fillRect(matrixStack, new Rectangle(new Coordinate(startX, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + (TensorOptions.shift ? barHeight + gapDist : 0)), clickWidth, clickHeight * CPSCounter.leftCPS()), new Color(0x64ffffff));
            DrawStuff.fillRect(matrixStack, new Rectangle(new Coordinate(startX + clickWidth + gapDist, startY + (TensorOptions.wASD ? wASDHeight * 2 + gapDist * 2 : 0) + (TensorOptions.mouse ? mouseHeight + gapDist : 0) + (TensorOptions.space ? barHeight + gapDist : 0) + (TensorOptions.shift ? barHeight + gapDist : 0)), clickWidth, clickHeight * CPSCounter.rightCPS()), new Color(0x64ffffff));
            matrixStack.pop();
        });
    }
    
    public static KeystrokesDrawer getInstance()
    {
        if(KeystrokesDrawer.keystrokesDrawer == null)
            KeystrokesDrawer.keystrokesDrawer = new KeystrokesDrawer(Tensor.client);
        return KeystrokesDrawer.keystrokesDrawer;
    }
}
