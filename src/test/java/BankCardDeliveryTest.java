import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class BankCardDeliveryTest {

    private String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }

    @Test

    public void shouldPassSuccessfully() {
        open("http://localhost:9999");

        $("[data-test-id='city']input").setValue("Тула");
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date']input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date']input").setValue(planningDate);
        $("[data-test-id='name']input").setValue("Евлампий Петров-Водкин");
        $("[data-test-id='phone']input").setValue("+76006007788");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на" + planningDate));



    }
}
