package tensor.util;

import tensor.option.TensorOptions;
import tensor.gui.KeybindsScreen;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TControlsListWidget extends ElementListWidget<TControlsListWidget.Entry>
{
    final KeybindsScreen parent;
    int maxKeyNameLength;
    
    public TControlsListWidget(KeybindsScreen parent, MinecraftClient client)
    {
        super(client, parent.width + 45, parent.height, 43, parent.height - 32, 20);
        this.parent = parent;
        KeyBinding[] keyBindings = ArrayUtils.clone(TensorOptions.allKeys);
        Arrays.sort(keyBindings);
        String string = null;
        for(KeyBinding keyBinding : keyBindings)
        {
            String string2 = keyBinding.getCategory();
            if(!string2.equals(string))
            {
                string = string2;
                this.addEntry(new CategoryEntry(Text.translatable(string2)));
            }
            Text text = Text.translatable(keyBinding.getTranslationKey());
            int i = client.textRenderer.getWidth(text);
            if(i > this.maxKeyNameLength)
            {
                this.maxKeyNameLength = i;
            }
            this.addEntry(new KeyBindingEntry(keyBinding, text));
        }
    }
    
    public class CategoryEntry extends TControlsListWidget.Entry
    {
        final Text text;
        private final int textWidth;
    
        public CategoryEntry(Text text)
        {
            this.text = text;
            this.textWidth = TControlsListWidget.this.client.textRenderer.getWidth(this.text);
        }
        
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta)
        {
            TextRenderer textRenderer = TControlsListWidget.this.client.textRenderer;
            Text text = this.text;
            float var10003 = (float)(TControlsListWidget.this.client.currentScreen.width / 2 - this.textWidth / 2);
            int var10004 = y + entryHeight;
            Objects.requireNonNull(TControlsListWidget.this.client.textRenderer);
            textRenderer.draw(matrices, text, var10003, (float)(var10004 - 9 - 1), 16777215);
        }
    
        public boolean changeFocus(boolean lookForwards)
        {
            return false;
        }
    
        public List<? extends Element> children()
        {
            return Collections.emptyList();
        }
    
        public List<? extends Selectable> selectableChildren()
        {
            return ImmutableList.of(new Selectable()
            {
                public Selectable.SelectionType getType()
                {
                    return Selectable.SelectionType.HOVERED;
                }
            
                public void appendNarrations(NarrationMessageBuilder builder)
                {
                    builder.put(NarrationPart.TITLE, TControlsListWidget.CategoryEntry.this.text);
                }
            });
        }
    }
    
    public class KeyBindingEntry extends TControlsListWidget.Entry
    {
        private final KeyBinding binding;
        private final Text bindingName;
        private final ButtonWidget editButton;
        private final ButtonWidget resetButton;
        
        KeyBindingEntry(final KeyBinding binding, final Text bindingName)
        {
            this.binding = binding;
            this.bindingName = bindingName;
            this.editButton = new ButtonWidget(0, 0, 75, 20, bindingName, (button) ->
            {
                TControlsListWidget.this.parent.focusedBinding = binding;
            })
            {
                protected MutableText getNarrationMessage()
                {
                    return binding.isUnbound() ? Text.translatable("narrator.controls.unbound", bindingName) : Text.translatable("narrator.controls.bound", bindingName, super.getNarrationMessage());
                }
            };
            this.resetButton = new ButtonWidget(0, 0, 50, 20, Text.translatable("controls.reset"), (button) ->
            {
                TControlsListWidget.this.client.options.setKeyCode(binding, binding.getDefaultKey());
                KeyBinding.updateKeysByCode();
            })
            {
                protected MutableText getNarrationMessage()
                {
                    return Text.translatable("narrator.controls.reset", bindingName);
                }
            };
        }
        
        public void render(MatrixStack matrices, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta)
        {
            boolean bl = TControlsListWidget.this.parent.focusedBinding == this.binding;
            TextRenderer var10000 = TControlsListWidget.this.client.textRenderer;
            float var10003 = (float)(x + 90 - TControlsListWidget.this.maxKeyNameLength);
            int var10004 = y + entryHeight / 2;
            Objects.requireNonNull(TControlsListWidget.this.client.textRenderer);
            var10000.draw(matrices, this.bindingName, var10003, (float)(var10004 - 9 / 2), 16777215);
            this.resetButton.x = x + 190;
            this.resetButton.y = y;
            this.resetButton.active = !this.binding.isDefault();
            this.resetButton.render(matrices, mouseX, mouseY, tickDelta);
            this.editButton.x = x + 105;
            this.editButton.y = y;
            this.editButton.setMessage(this.binding.getBoundKeyLocalizedText());
            boolean bl2 = false;
            if(!this.binding.isUnbound())
            {
                KeyBinding[] var13 = TControlsListWidget.this.client.options.allKeys;
                for(KeyBinding keyBinding : var13)
                {
                    if(keyBinding != this.binding && this.binding.equals(keyBinding))
                    {
                        bl2 = true;
                        break;
                    }
                }
                KeyBinding[] var14 = TensorOptions.allKeys;
                for(KeyBinding keyBinding : var14)
                {
                    if(keyBinding != this.binding && this.binding.equals(keyBinding))
                    {
                        bl2 = true;
                        break;
                    }
                }
            }
            if(bl)
            {
                this.editButton.setMessage((Text.literal("> ")).append(this.editButton.getMessage().copy().formatted(Formatting.YELLOW)).append(" <").formatted(Formatting.YELLOW));
            }
            else if(bl2)
            {
                this.editButton.setMessage(this.editButton.getMessage().copy().formatted(Formatting.RED));
            }
            this.editButton.render(matrices, mouseX, mouseY, tickDelta);
        }
        
        public List<? extends Element> children()
        {
            return ImmutableList.of(this.editButton, this.resetButton);
        }
        
        public List<? extends Selectable> selectableChildren()
        {
            return ImmutableList.of(this.editButton, this.resetButton);
        }
        
        public boolean mouseClicked(double mouseX, double mouseY, int button)
        {
            if(this.editButton.mouseClicked(mouseX, mouseY, button))
            {
                return true;
            }
            else
            {
                return this.resetButton.mouseClicked(mouseX, mouseY, button);
            }
        }
        
        public boolean mouseReleased(double mouseX, double mouseY, int button)
        {
            return this.editButton.mouseReleased(mouseX, mouseY, button) || this.resetButton.mouseReleased(mouseX, mouseY, button);
        }
    }
    
    public abstract static class Entry extends ElementListWidget.Entry<TControlsListWidget.Entry>
    {
    }
}
