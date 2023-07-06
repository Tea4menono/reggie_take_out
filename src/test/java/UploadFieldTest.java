import org.junit.jupiter.api.Test;

public class UploadFieldTest {
    @Test
    public void test1() {
        String filename = "12321.jpg";
        String suffix = filename.substring(filename.lastIndexOf("."));
        System.out.println(suffix);
    }
}
