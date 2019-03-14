package checkForExistingData;

import java.nio.file.Files;
import java.nio.file.Path;

public class UsernameCheck
{
    public boolean userNameInDataBaseIs(Path dataBasePath)
    {
        if (Files.exists(dataBasePath))
            return true;
        else
            return false;
    }
}
