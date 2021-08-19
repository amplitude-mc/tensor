package tensor.option;

import tensor.Tensor;

import java.lang.reflect.Field;

public class ParsedSetting<T>
{
    public final Field field;
    public final String name;
    public final SettingsManager settingsManager;
    public final Class<T> type;
    public final T defaultValue;
    public final String[] categories;
    
    public ParsedSetting(Field field, Setting setting, SettingsManager settingsManager)
    {
        this.field = field;
        this.name = field.getName();
        this.settingsManager = settingsManager;
        this.type = (Class<T>) field.getType();
        this.defaultValue = get();
        this.categories = setting.value();
    }
    
    public T get()
    {
        try
        {
            return (T) this.field.get(null);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public ParsedSetting<T> set(String value)
    {
        Tensor.logger.info("Currently setting the setting");
        if(type == String.class)
        {
            return set((T) value);
        }
        else if(type == boolean.class)
        {
            return set((T) (Object) Boolean.parseBoolean(value));
        }
        else if(type == int.class)
        {
            return set((T) (Object) Integer.parseInt(value));
        }
        else if(type == double.class)
        {
            return set((T) (Object) Double.parseDouble(value));
        }
        else
        {
            return null;
        }
    }
    
    public ParsedSetting<T> set(T value)
    {
        if(!value.equals(get()))
        {
            try
            {
                this.field.set(null, value);
                TensorOptions.class.getField(this.name).set(null, value);
            }
            catch(IllegalAccessException | NoSuchFieldException e)
            {
                e.printStackTrace();
            }
        }
        return this;
    }
}
