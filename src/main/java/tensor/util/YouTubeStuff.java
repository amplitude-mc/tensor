package tensor.util;

import tensor.Tensor;
import tensor.option.TensorOptions;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class YouTubeStuff
{
    private final String uri;
    private final Map<String, String> parameters = new HashMap<>();
    
    private YouTubeStuff(String uri)
    {
        this.uri = uri;
    }
    
    public static String getSubs(String id)
    {
        if(id.equals("") || TensorOptions.youtubeAPIKey.equals(""))
            return "";
        String uri = "https://www.googleapis.com/youtube/v3/channels";
        YouTubeStuff request = new YouTubeStuff(uri);
        request
            .addParameter("part", "statistics")
            .addParameter("id", id)
            .addParameter("key", TensorOptions.youtubeAPIKey);
        String jsonData = request.execute();
        if(jsonData == null)
            return "";
        int i = jsonData.indexOf("subscriberCount");
        char ch;
        String originalSubs = "";
        while((ch = jsonData.charAt((i++) + 19)) != '"')
            originalSubs += ch;
        float fSubs = 0.0F;
        try
        {
            fSubs = Float.parseFloat(originalSubs);
        }
        catch(NumberFormatException e)
        {
            Tensor.logger.error("Error reading youtube data");
        }
        int tag = 0;
        if(fSubs >= 1000000000)
        {
            fSubs /= 1000000000;
            tag = 3;
        }
        else if(fSubs >= 1000000)
        {
            fSubs /= 1000000;
            tag = 2;
        }
        else if(fSubs >= 1000)
        {
            fSubs /= 1000;
            tag = 1;
        }
        String subs = String.valueOf(fSubs);
        int count = subs.length() - 1;
        while(subs.charAt(count) == '0' || subs.charAt(count) == '.')
        {
            char c = subs.charAt(count);
            subs = subs.substring(0, count--);
            if(c == '.')
                break;
        }
        switch(tag)
        {
            case 3 -> subs += 'B';
            case 2 -> subs += 'M';
            case 1 -> subs += 'K';
            case 0 -> {}
        }
        return subs;
    }
    
    private String execute()
    {
        try
        {
            URL url = new URL(this.uri + getParamsString(this.parameters));
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            if(con != null)
            {
                try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream())))
                {
                    String input;
                    StringBuilder content = new StringBuilder();
                    while((input = br.readLine()) != null)
                    {
                        content.append(input);
                    }
                    return content.toString();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                con.disconnect();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    private YouTubeStuff addParameter(String name, String value)
    {
        this.parameters.put(name, value);
        return this;
    }
    
    static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, String> entry : params.entrySet())
        {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }
        String resultString = result.toString();
        return resultString.length() > 0 ? '?' + resultString.substring(0, resultString.length() - 1) : resultString;
    }
}
