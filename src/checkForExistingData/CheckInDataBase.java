package checkForExistingData;

import java.nio.file.Files;
import java.nio.file.Path;

public class CheckInDataBase
{
    public boolean forUsername(Path dataBasePath)
    {
        return Files.exists(dataBasePath);
    }
}
