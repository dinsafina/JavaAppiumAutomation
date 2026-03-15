import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest extends CoreTestCase{

    @BeforeAll
    public static void textStart(){
        System.out.println("Start test");
    }

    @Test
    public void myFirstTest(){
        System.out.println("First test");
    }

    @Test
    public void mySecondTest(){
        System.out.println("Second test");
    }
}
