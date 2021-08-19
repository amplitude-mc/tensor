package tensor.mixin.optimization;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Quaternion.class)
public class Optimization_Quaternion
{
    @Overwrite
    private static float sin(float x)
    {
        return MathHelper.sin(x);
    }
    
    @Overwrite
    private static float cos(float x)
    {
        return MathHelper.cos(x);
    }
}
