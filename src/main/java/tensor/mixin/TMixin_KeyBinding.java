package tensor.mixin;

import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(KeyBinding.class)
public class TMixin_KeyBinding
{
    @Shadow
    @Final
    private static Map<String, Integer> CATEGORY_ORDER_MAP;
    
    @Inject(method = "<init>", at = @At("RETURN"))
    private void addCategories(CallbackInfo info)
    {
        TMixin_KeyBinding.CATEGORY_ORDER_MAP.put("key.category.utility", 8);
        TMixin_KeyBinding.CATEGORY_ORDER_MAP.put("key.category.miscellaneous", 9);
        TMixin_KeyBinding.CATEGORY_ORDER_MAP.put("key.category.toggles", 10);
    }
}