import com.oplogo.kira.config.AppConfig;
import com.oplogo.kira.config.DispatcherConfig;
import com.oplogo.kira.model.KiraEntity;
import com.oplogo.kira.service.EntityService;
import com.oplogo.kira.util.StringUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

/**
 * Created by yy on 5/29/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(name="parent",classes = AppConfig.class),
        @ContextConfiguration(name="child",classes = DispatcherConfig.class)
})
public class DummyTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Autowired
    private EntityService entityService;

    @Test
    public void save(){
        KiraEntity entity = new KiraEntity();
        entity.setName("aaa");
        entity.setPackageName("bbb");
        entity.setCode("ccc");

        assertEquals(1,entityService.findAll().size());

    }
}
