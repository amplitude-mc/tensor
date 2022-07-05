package tensor.util.math;

public class DevmasterMath
{
    public static final float PI = 3.1415927f;
    public static final float PI_2 = PI / 2f;
    public static final float DOUBLE_PI = PI * 2f;
    public static final float B = 4 / PI;
    public static final float C = -4 / (PI * PI);
    public static final float P = 0.225f;
    
    public static float sin(float x)
    {
        float x1 = x % DevmasterMath.PI;
        float x2 = x % DevmasterMath.DOUBLE_PI;
    
        float y;
        if(x > 0)
        {
            y = x1 * (DevmasterMath.B + DevmasterMath.C * x1);
            y = (y > 0) ? DevmasterMath.P * (y * y - y) + y : DevmasterMath.P * (-y * y - y) + y;
            float xp = x2 - DevmasterMath.DOUBLE_PI;
            if(xp >= -DevmasterMath.PI)
                y = -y;
        } else
        {
            y = x1 * (DevmasterMath.B - DevmasterMath.C * x1);
            y = (y > 0) ? DevmasterMath.P * (y * y - y) + y : DevmasterMath.P * (-y * y - y) + y;
            float xp = x2 + DevmasterMath.DOUBLE_PI;
            if(xp > 0 && xp < DevmasterMath.PI)
                y = -y;
        }
        return y;
    }
    
    public static float cos(float x)
    {
        float x0 = x + DevmasterMath.PI_2;
        float x1 = x0 % DevmasterMath.PI;
        float x2 = x0 % DevmasterMath.DOUBLE_PI;
    
        float y;
        if(x0 > 0)
        {
            y = x1 * (DevmasterMath.B + DevmasterMath.C * x1);
            y = (y > 0) ? DevmasterMath.P * (y * y - y) + y : DevmasterMath.P * (-y * y - y) + y;
            float xp = x2 - DevmasterMath.DOUBLE_PI;
            if(xp >= -DevmasterMath.PI)
                y = -y;
        } else
        {
            y = x1 * (DevmasterMath.B - DevmasterMath.C * x1);
            y = (y > 0) ? DevmasterMath.P * (y * y - y) + y : DevmasterMath.P * (-y * y - y) + y;
            float xp = x2 + DevmasterMath.DOUBLE_PI;
            if(xp > 0 && xp < DevmasterMath.PI)
                y = -y;
        }
        return y;
    }
}
