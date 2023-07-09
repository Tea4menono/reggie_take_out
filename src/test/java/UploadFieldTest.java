import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;

public class UploadFieldTest {
    @Test
    public void test1() {

        Dotenv dotenv = Dotenv.load();
        System.out.println(dotenv.get("ACCESS_KEY"));
        System.out.println(dotenv.get("ACCESS_PASSWORD"));

    }
}
