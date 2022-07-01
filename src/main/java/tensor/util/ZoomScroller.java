package tensor.util;

import tensor.option.TensorOptions;

public class ZoomScroller
{
    private static double zoom = TensorOptions.zoomFOV;
    
    public static void addScroll(double scroll)
    {
        double wouldBe = ZoomScroller.zoom - 2 * scroll;
        if(wouldBe < 180 && wouldBe > 0)
            ZoomScroller.zoom = wouldBe;
    }
    
    public static void resetScroll()
    {
        ZoomScroller.zoom = TensorOptions.zoomFOV;
    }
    
    public static double getZoom()
    {
        return ZoomScroller.zoom;
    }
}
