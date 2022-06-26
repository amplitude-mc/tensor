package tensor.util;

public class Coordinate
{
    public static final Coordinate ZERO = new Coordinate(0, 0);
    
    private final int x;
    private final int y;
    
    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
}
