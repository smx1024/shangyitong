import com.sx.yygh.user.client.SystemLog;
import org.junit.Test;

public class a {
    @Test
    @SystemLog
    public void test() {
        System.out.println("test");
    }
}
