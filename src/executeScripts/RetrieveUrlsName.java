package executeScripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RetrieveUrlsName
{
    public String getNameFrom(String url) throws IOException
    {
        Process process = Runtime.getRuntime().exec("python src/pythonScripts/mySpider.py "+ url);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        line = bufferedReader.readLine();

        return line;
    }
}
