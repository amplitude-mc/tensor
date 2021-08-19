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
    private static Map<String, Integer> categoryOrderMap;
    
    @Inject(method = "<init>", at = @At("RETURN"))
    private void addCategories(CallbackInfo info)
    {
        categoryOrderMap.put("key.category.utility", 8);
        categoryOrderMap.put("key.category.miscellaneous", 9);
        categoryOrderMap.put("key.category.toggles", 10);
    }
}