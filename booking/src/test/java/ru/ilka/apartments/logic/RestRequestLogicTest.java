package ru.ilka.apartments.logic;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.*;
import static org.mockito.Mockito.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.ilka.apartments.TestConfig;
import ru.ilka.apartments.model.command.CommandType;
import ru.ilka.apartments.model.entity.RestRequest;
import ru.ilka.apartments.model.logic.RestRequestLogic;

import java.util.*;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
@RunWith(Parameterized.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class RestRequestLogicTest {
    private static final String COMMAND_PARAM = "command";

    private RestRequest request;
    private String method;
    private String pathInfo;
    private CommandType expectedCommandType;

    // cause can't be more then 1 @@RunWith
    //@RunWith(SpringJUnit4ClassRunner.class) ~ rules :)
    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private RestRequestLogic restRequestLogic;

    public RestRequestLogicTest(String method, String pathInfo, CommandType expectedCommandType) {
        this.method = method;
        this.pathInfo = pathInfo;
        this.expectedCommandType = expectedCommandType;
    }

    @Parameterized.Parameters(name = "{index}: method - {0}, uri - {1}, command - {2}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"GET","apartments/",CommandType.SHOW_ALL},
                {"DELETE","apartments/",CommandType.DELETE_ALL},
                {"GET","apartments/available",CommandType.SHOW_AVAILABLE},
                {"POST","apartment/",CommandType.ADD},
                {"GET","apartment/3",CommandType.SHOW_BY_ID},
                {"DELETE","apartment/3",CommandType.DELETE},
                {"PUT","apartment/3",CommandType.BOOK},
                {"PUT","apartment/3/days/5",CommandType.BOOK},
        });
    }

    @Before
    public void initRequest(){
        javax.servlet.http.HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        request = new RestRequest(httpServletRequest);
    }

    @Test
    public void checkIfParametersAreCorrect(){
        when(request.getMethod()).thenReturn(method);
        when(request.getPathInfo()).thenReturn(pathInfo);
        when(request.getParameter(COMMAND_PARAM)).thenReturn(expectedCommandType.toString());
        Assert.assertEquals(expectedCommandType,CommandType.valueOf(restRequestLogic.checkCommand(request).getParameter(COMMAND_PARAM)));
    }
}
