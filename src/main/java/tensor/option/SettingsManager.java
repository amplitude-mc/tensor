package tensor.option;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import tensor.Tensor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager
{
    public Map<String, ParsedSetting<?>> settings = new HashMap<>();
    
    private final String id;
    
    public SettingsManager(String id)
    {
        this.id = id;
    }
    
    public Path getPath()
    {
        return MinecraftClient.getInstance().runDirectory.toPath().getParent().resolve(id + ".conf");
    }
    
    public void parseSettingsClass(Class settingsClass)
    {
        for(Field field : settingsClass.getFields())
        {
            Setting setting = field.getAnnotation(Setting.class);
            if(setting == null)
                continue;
            ParsedSetting parsedSetting = new ParsedSetting(field, setting, this);
            settings.put(parsedSetting.name, parsedSetting);
        }
    }
    
    public void loadSettings()
    {
        Map<String, String> conf = readSettings(getPath());
        for(String key : conf.keySet())
        {
            try
            {
                if(settings.get(key).set(conf.get(key)) != null)
                {
                    Field field = TensorOptions.class.getField(key);
                    Key annotation = field.getAnnotation(Key.class);
                    if(annotation == null)
                        continue;
                    for(KeyBinding keybind : TensorOptions.allKeys)
                    {
                        if(annotation.value().equals(keybind.getTranslationKey()))
                        {
                            keybind.setBoundKey(InputUtil.Type.KEYSYM.createFromCode(Integer.parseInt(conf.get(key))));
                            Tensor.logger.info(key + " is a keybind");
                        }
                    }
                }
            }
            catch(Exception e)
            {
                Tensor.logger.error("Failed to load setting " + key + ": " + e);
            }
        }
    }
    
    private Map<String, String> readSettings(Path path)
    {
        try(BufferedReader reader = Files.newBufferedReader(path))
        {
            String line = "";
            Map<String, String> result = new HashMap<>();
            while((line = reader.readLine()) != null)
            {
                line = line.replaceAll("[\\r\\n]", "");
                String[] fields = line.split("\\s+", 2);
                if(fields.length > 1)
                {
                    if(!settings.containsKey(fields[0]))
                    {
                        continue;
                    }
                    result.put(fields[0], fields[1]);
                }
            }
            return result;
        }
        catch(NoSuchFileException e)
        {
            try
            {
                Files.createFile(path);
                return readSettings(path);
            }
            catch(IOException e2)
            {
                e2.printStackTrace();
            }
            return new HashMap<>();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    
    private void writeSettings(Map<String, String> values)
    {
        try(BufferedWriter writer = Files.newBufferedWriter(this.getPath()))
        {
            for(String key : values.keySet())
            {
                if(values.get(key).equals(settings.get(key).defaultValue.toString()))
                {
                    writer.newLine();
                    continue;
                }
                writer.write(key + " " + values.get(key));
                writer.newLine();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void setSetting(String strSetting, String stringValue)
    {
        Tensor.logger.info("Setting [" + strSetting + "] is being set to: " + stringValue);
        ParsedSetting<?> setting = settings.get(strSetting);
        if(setting == null)
            return;
        setting.set(stringValue);
        Map<String, String> conf = readSettings(getPath());
        conf.put(setting.name, stringValue);
        writeSettings(conf);
    }
}
