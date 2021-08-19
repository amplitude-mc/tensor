package tensor.util;

import tensor.Tensor;
import tensor.hud.TextDrawer;
import tensor.option.TensorOptions;

public class SubGetterThread implements Runnable
{
    Thread t;
    
    public SubGetterThread()
    {
        t = new Thread(this);
        t.start();
    }
    
    @Override
    public void run()
    {
        try
        {
            while(Tensor.client.isRunning())
            {
                TextDrawer.subs = YouTubeStuff.getSubs(TensorOptions.youtubeID);
                Thread.sleep(12000);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("interrupted");
        }
    }
}
