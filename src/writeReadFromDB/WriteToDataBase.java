package writeReadFromDB;

import Dialogs.ExceptionDialog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class WriteToDataBase
{
    public void username(Path dBPath, String[] names)
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dBPath.toString()));
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
}
