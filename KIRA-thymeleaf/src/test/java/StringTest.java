import com.oplogo.kira.util.RegexUtils;
import com.oplogo.kira.util.StringUtil;
import org.junit.Test;

/**
 * Created by yy on 6/5/14.
 */
public class StringTest {
    @Test
    public void a() {
        String s = "whatAFuckingDay!";
        System.out.println(s.replaceAll(RegexUtils.CAMEL_SPLIT, " "));
        System.out.println(StringUtil.splitCamelCase(s," "));
    }
}
