package writeReadFromDB;

import Dialogs.ExceptionDialog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class WriteToDataBase
{
    public void username(Path dbPath, String[] names)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath.toString()));
            writer.write(names[0]);
            writer.newLine();
            writer.write(names[1]);
            writer.close();

        }catch (IOException e )
        {
            ExceptionDialog exceptionDialog = new ExceptionDialog();
            exceptionDialog.popDialog("WriteToDataBase > username");
            e.printStackTrace();
        }

    }


    public void urls(Path dbPath , List<String> urls)
    {
        StringBuilder stringToWriteInDb = new StringBuilder("start chrome ");
        for (String url:urls)
            stringToWriteInDb.append("\"").append(url).append("\"").append(" ");

        System.out.println(stringToWriteInDb);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath.toString()));
            writer.write(stringToWriteInDb.toString());
            writer.close();

        }catch (IOException e )
        {
            ExceptionDialog exceptionDialog = new ExceptionDialog();
            exceptionDialog.popDialog("WriteToDataBase > urls");
            e.printStackTrace();
        }
    }


    public void urlNames(Path dbPath , List<String> names)
    {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(dbPath.toString()));
            for (String name:names)
            {
                writer.write(name);
                writer.newLine();
            }
            writer.close();

        }catch (IOException e)
        {
            ExceptionDialog exceptionDialog = new ExceptionDialog();
            exceptionDialog.popDialog("WriteToDataBase > urlNames");
            e.printStackTrace();
        }
    }
}
