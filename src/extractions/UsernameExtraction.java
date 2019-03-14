package extractions;

import Dialogs.ExceptionDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UsernameExtraction
{

    String line;

    public String[] getTheFullUsernameAndTheHalfOne()
    {
        String[] returnArray = new String[2];

            try{
                ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "whoami");
                processBuilder.redirectErrorStream(true);
                Process p = processBuilder.start();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(p.getInputStream()));


                while ((line = bufferReader.readLine()) != null)
                {
                    returnArray[0] = line;

                    if (line.indexOf('\\') != -1)
                    {
                        int counter = 0;
                        do
                        {
                            counter++;
                            int linesLength = line.length();
                            line = line.substring(line.indexOf('\\')+1,linesLength);
                            returnArray[counter] = line;
                        }while (line.indexOf('\\') != -1);
                    }


            }

            }catch (IOException e)
            {
                ExceptionDialog exceptionDialog = new ExceptionDialog();
                exceptionDialog.popDialog("UsernameExtraction > getTheFullUsernameAndTheHalfOne ");
                e.printStackTrace();
            }

        return returnArray;
    }



}
