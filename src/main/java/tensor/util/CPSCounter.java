package tensor.util;

import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class CPSCounter
{
    private static final ClickList left = new ClickList();
    private static final ClickList right = new ClickList();
    
    public static void click(int button)
    {
        if(button == 0)
            left.click();
        else if(button == 1)
            right.click();
    }
    
    public static void tick()
    {
        left.tick();
        right.tick();
    }
    
    public static int leftCPS()
    {
        return left.getClicks();
    }
    
    public static int rightCPS()
    {
        return right.getClicks();
    }
    
    private static class ClickList
    {
        private final List<Long> list;
        
        protected ClickList()
        {
            list = new ArrayList<>();
        }
        
        protected void tick()
        {
            list.removeIf((click) -> Util.getMeasuringTimeMs() - click > 1000);
        }
        
        protected void click()
        {
            list.add(Util.getMeasuringTimeMs());
        }
        
        protected int getClicks()
        {
            return list.size();
        }
    }
}
