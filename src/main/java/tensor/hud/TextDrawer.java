package tensor.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import tensor.Tensor;
import tensor.option.TensorOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextDrawer implements Drawer
{
    public static String subs;
    private static TextDrawer textDrawer;
    private MinecraftClient client;
    private static final List<String> texts = new ArrayList<>();
    
    private TextDrawer(MinecraftClient client)
    {
        this.client = client;
    }
    
    @Override
    public void draw()
    {
        this.makeSubText();
        this.makeSprintText();
    }
    
    private void makeSubText()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.subCounter)
                return;
            int count = 0;
            for(int i = 0; i < TextDrawer.texts.size(); i++)
            {
                if(TextDrawer.texts.get(i).equals("subCounter"))
                    break;
                try
                {
                    if((boolean) Tensor.settingsManager.settings.get(TextDrawer.texts.get(i)).field.get(null))
                        count++;
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            int m = this.client.getWindow().getHeight() / 3 + 9 * count;
            TextRenderer renderer = this.client.textRenderer;
            DrawableHelper.fill(matrixStack, 1, m - 1, renderer.getWidth(TensorOptions.subscribersText + TextDrawer.subs) + 3, m + 8, -1873784752);
            renderer.draw(matrixStack, TensorOptions.subCounter ? TensorOptions.subscribersText : "", 2, m, 0xffffff);
            renderer.draw(matrixStack, TensorOptions.subCounter ? TextDrawer.subs : "", renderer.getWidth(TensorOptions.subscribersText) + 2, m, 0x00ff00);
        });
    }
    
    private void makeSprintText()
    {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) ->
        {
            if(!TensorOptions.sprintingText)
                return;
            int count = 0;
            for(int i = 0; i < TextDrawer.texts.size(); i++)
            {
                if(TextDrawer.texts.get(i).equals("sprintingText"))
                    break;
                try
                {
                    if((boolean) Tensor.settingsManager.settings.get(TextDrawer.texts.get(i)).field.get(null))
                        count++;
                }
                catch(IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
            int m = this.client.getWindow().getHeight() / 3 + 9 * count;
            TextRenderer renderer = this.client.textRenderer;
            String text = "Sprinting: " + (TensorOptions.permanentSprint ? "permanent" : "vanilla");
            DrawableHelper.fill(matrixStack, 1, m - 1, renderer.getWidth(text) + 3, m + 8, -1873784752);
            renderer.draw(matrixStack, "Sprinting: ", 2, m, 0xffffff);
            renderer.draw(matrixStack, TensorOptions.permanentSprint ? "permanent" : "vanilla", renderer.getWidth("Sprinting: ") + 2, m, 0x00ff00);
        });
    }
    
    protected static TextDrawer getInstance()
    {
        if(TextDrawer.textDrawer == null)
            TextDrawer.textDrawer = new TextDrawer(Tensor.client);
        return textDrawer;
    }
    
    static
    {
        TextDrawer.texts.add("subCounter");
        TextDrawer.texts.add("sprintingText");
        Collections.sort(TextDrawer.texts);
    }
}
