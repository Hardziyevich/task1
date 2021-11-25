package by.hardziyevich.task.validator;

import by.hardziyevich.task.exeption.SomeException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTxtTest {
    String resourceName = "1.txt";
    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource(resourceName).getFile());
    String absolutePath = file.getAbsolutePath();

    @Test
    void testExceptionBuilder(){
        String randomPath = "asdas";
        assertThrows(SomeException.class,()->new ValidatorTxt(Path.of(randomPath)).checkTxt());
    }

    @Test
    void testBuilder() throws SomeException {
        ValidatorTxt validatorTxt = new ValidatorTxt(Path.of(absolutePath));
        Path builder = null;
        builder = validatorTxt.checkTxt();
        assertEquals(absolutePath,builder.toFile().getPath());
    }
}
