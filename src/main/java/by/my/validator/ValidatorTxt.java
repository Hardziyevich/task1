package by.my.validator;

import by.my.exeption.SomeException;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Just check file exist and .txt
 */
public class ValidatorTxt{

    private Path path;
    private Predicate<Path> checkFormat = x -> x.toFile().getAbsolutePath().endsWith(".txt");
    private Predicate<Path> checkExist = x -> x.toFile().exists();

    public ValidatorTxt(Path path){
        this.path = path;
    }

    public Path builder() throws SomeException {
        return Validator.of(path).validate(checkFormat,"It`s not txt")
                .validate(checkExist, "It doesn`t exist")
                .get();
    }
}
