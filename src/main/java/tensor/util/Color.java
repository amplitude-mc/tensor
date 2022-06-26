package tensor.util;

public class Color
{
    private final int r;
    private final int g;
    private final int b;
    private final int alpha;
    private final int color;
    
    public Color(int color)
    {
        this((color >> 16) & 0xff, (color >> 8) & 0xff, color & 0xff, (color >> 24) & 0xff);
    }
    
    public Color(int r, int g, int b, int alpha)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
        this.color = (alpha << 24) | ((r << 16) | ((g << 8) | b));
    }
    
    public int getColor()
    {
        return this.color;
    }
    
    public Color blend(Color blend, float progress)
    {
        if(progress <= 0)
            return this;
        if(progress >= 1)
            return blend;
        int r = Color.blendInt(this.getR(), blend.getR(), progress);
        int g = Color.blendInt(this.getG(), blend.getG(), progress);
        int b = Color.blendInt(this.getB(), blend.getB(), progress);
        int alpha = Color.blendInt(this.getAlpha(), blend.getAlpha(), progress);
        return new Color(r, g, b, alpha);
    }
    
    public static int blendInt(int lower, int upper, float progress)
    {
        if(lower == upper)
            return lower;
        if(progress <= 0)
            return lower;
        if(progress >= 1)
            return upper;
        return lower + Math.round((float) (upper - lower) * progress);
    }
    
    public int getR()
    {
        return this.r;
    }
    
    public int getG()
    {
        return this.g;
    }
    
    public int getB()
    {
        return this.b;
    }
    
    public int getAlpha()
    {
        return this.alpha;
    }
    
    @Override
    public String toString()
    {
        return "Alpha: " + this.alpha + " R: " + this.r + " G: " + this.g + " B: " + this.b;
    }
}
