package writeReadFromDB;

import Dialogs.ExceptionDialog;

import java.io.*;
import java.nio.file.Path;

public class ReadFromDataBase
{
    public String[] readUsername(Path dBPath)
    {
        String returnArray[]= new String[2];

        try (BufferedReader reader = new BufferedReader(new FileReader(dBPath.toString())))
        {

            int counter = 0;
            String line;
            while ((line = reader.readLine()) != null)
            {
                returnArray[counter]=line;
                counter++;
            }

        } catch (IOException e)
        {
            ExceptionDialog exceptionDialog = new ExceptionDialog();
            exceptionDialog.popDialog("ReadFromDataBase > ReadUsername");
            e.printStackTrace();

        }
        return returnArray;
    }

}


