package tensor.hud;

import java.util.ArrayList;
import java.util.List;

public class Drawers
{
    private static final List<Drawer> drawers = new ArrayList<>();
    
    public static void drawAll()
    {
        for(Drawer drawer : Drawers.drawers)
            drawer.draw();
    }
    
    static
    {
        drawers.add(TextDrawer.getInstance());
        drawers.add(KeystrokesDrawer.getInstance());
    }
}
