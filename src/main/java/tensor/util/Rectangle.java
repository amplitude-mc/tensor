package tensor.util;

public class Rectangle
{
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    
    public Rectangle(Coordinate coordinate, int width, int height)
    {
        this(coordinate.getX(), coordinate.getY(), width, height);
    }
    
    public Rectangle(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle offset(Coordinate offset)
    {
        return new Rectangle(this.x + offset.getX(), this.y + offset.getY(), this.width, this.height);
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
}
