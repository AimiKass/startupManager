package writeReadFromDB;

import Dialogs.ExceptionDialog;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReadFromDataBase
{

    public String[] readUsername(Path dBPath)
    {
        String returnArray[]= new String[2];

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(dBPath.toString()));

            int counter = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                returnArray[counter]=line;
                counter++;
            }

            reader.close();

        } catch (IOException ignored)
        {


        }
        return returnArray;
    }




    public List<String> readUrls(Path foldersPath)
    {
        List<String> urls =new ArrayList<>();
        String line = null;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(foldersPath.toString()));
            line = reader.readLine();
            reader.close();
        }catch (IOException e)
        {
            ExceptionDialog exceptionDialog = new ExceptionDialog();
            exceptionDialog.popDialog("ReadFromDataBase > readUrls");
            e.printStackTrace();
        }

        if (line != null)
        {
            StringBuilder stringBuilder = new StringBuilder(line);

            do {

                int firstQuote = stringBuilder.toString().indexOf("\"");
                if (firstQuote == -1)
                    break;

                stringBuilder.deleteCharAt(firstQuote);
                int secondQuote = stringBuilder.toString().indexOf("\"");
                stringBuilder.deleteCharAt(secondQuote);

                urls.add(stringBuilder.toString().substring(firstQuote,secondQuote));

            }while (true);

        }

        return urls;
    }


    public List<String> readUrlNames(Path fileName)
    {
        List<String> names = new ArrayList<>();

        try{

            BufferedReader reader = new BufferedReader(new FileReader(fileName.toString()));
            String name;

            while ((name = reader.readLine()) != null)
                names.add(name);

            reader.close();

        }catch (IOException e)
        {
            ExceptionDialog exceptionDialog = new ExceptionDialog();
            exceptionDialog.popDialog("ReadFromDataBase > readUrlNames");
            e.printStackTrace();
        }


        return names;
    }

}


