package by.hardziyevich.task.validator;

import by.hardziyevich.task.exeption.SomeException;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTxtTest {

    @Test
    void testExceptionBuilder(){
        String randomPath = "asdas";
        assertThrows(SomeException.class,()->new ValidatorTxt(Path.of(randomPath)).checkTxt());
    }

    @Test
    void testBuilder() throws SomeException {
        String filePath = "D:\\TaskForEpam\\task1\\src\\test\\resources\\1.txt";
        ValidatorTxt validatorTxt = new ValidatorTxt(Path.of(filePath));
        Path builder = null;
        builder = validatorTxt.checkTxt();
        assertEquals(filePath,builder.toFile().getPath());
    }
}
