package by.itacademy.todolist.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword validPassword) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        final PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 255),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Alphabetical, 1),
                new WhitespaceRule()
        ));

        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        List<String> messages = passwordValidator.getMessages(result);
        String messageTemplate = String.join(",", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();

        return false;
    }
}