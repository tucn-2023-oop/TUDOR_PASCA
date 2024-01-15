import com.example.passwordmanager.GeneratorController;
import javafx.application.Platform;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

public class GeneratorTests {
    @Test
    public void generateDigitsOnly() {
        Platform.startup(() -> {
            GeneratorController generatorController = new GeneratorController();
            RadioButton digitsRadio = new RadioButton();
            digitsRadio.setSelected(true);
            generatorController.setDigitsRadio(digitsRadio);
            Slider slider = new Slider();
            slider.setValue(25.0);
            String password = generatorController.generatePassword().toString();
            Assertions.assertTrue(password.matches("[0-9]+"));
        });
    }

    @Test
    public void generateCapitalLettersOnly() {
        Platform.startup(() -> {
            GeneratorController generatorController = new GeneratorController();
            RadioButton radio = new RadioButton();
            radio.setSelected(true);
            generatorController.setCapitalLettersRadio(radio);
            Slider slider = new Slider();
            slider.setValue(25.0);
            String password = generatorController.generatePassword().toString();
            Assertions.assertTrue(password.matches("[A-Z]+"));
        });
    }

    @Test
    public void generateDigitsAndLowercaseLetters() {
        Platform.startup(() -> {
            GeneratorController generatorController = new GeneratorController();
            RadioButton radio = new RadioButton();
            radio.setSelected(true);
            generatorController.setDigitsRadio(radio);
            generatorController.setSmallLettersRadio(radio);
            Slider slider = new Slider();
            slider.setValue(25.0);
            String password = generatorController.generatePassword().toString();
            Assertions.assertTrue(password.matches("^[0-9a-z]+$"));
        });
    }

    @Test
    public void generateNoSelectedCharacters() {
        Platform.startup(() -> {
            GeneratorController generatorController = new GeneratorController();
            String password = generatorController.generatePassword().toString();
            Assertions.assertTrue(password.isEmpty());
        });
    }

    @Test
    public void generateNoLength() {
        Platform.startup(() -> {
            GeneratorController generatorController = new GeneratorController();
            RadioButton radio = new RadioButton();
            radio.setSelected(true);
            generatorController.setDigitsRadio(radio);
            generatorController.setSmallLettersRadio(radio);
            Slider slider = new Slider();
            slider.setValue(0.0);
            String password = generatorController.generatePassword().toString();
            Assertions.assertTrue(password.isEmpty());
        });
    }
}
