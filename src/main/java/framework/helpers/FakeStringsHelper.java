package framework.helpers;

import com.github.javafaker.Faker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FakeStringsHelper {

  private static final Faker faker = new Faker();

  public static String generateFakeFirstName() {
    return faker.name().firstName();
  }

  public static String generateFakeLastName() {
    return faker.name().lastName();
  }

  public static String generateFakeEmail() {
    return faker.internet().emailAddress();
  }

  public static String generateFakePassword() {
    return faker.internet().password();
  }

  public static String generateScreenshotName() {
    return faker.file().fileName();
  }

  public static String generateFakeDate() {
    Date fakeBirthdate = faker.date().birthday();
    SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
    return DateFor.format(fakeBirthdate);
  }

}
