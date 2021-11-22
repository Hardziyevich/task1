package by.hardziyevich.task.validator;

import by.hardziyevich.task.exeption.SomeException;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * Just check file exist and .txt
 */
public class ValidatorTxt{

    private final Path path;
    private final Predicate<Path> checkFormat = x -> x.toFile().getAbsolutePath().endsWith(".txt");
    private final Predicate<Path> checkExist = x -> x.toFile().exists();

    public ValidatorTxt(Path path){
        this.path = path;
    }

    public Path checkTxt() throws SomeException {
        return Validator.of(path).validate(checkFormat,"It`s not txt")
                .validate(checkExist, "It doesn`t exist")
                .get();
    }
}
