package ru.ilka.apartments.logic;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.ilka.apartments.WebTestConfig;
import ru.ilka.apartments.command.CommandType;
import ru.ilka.apartments.rest.RestRequest;
import ru.ilka.apartments.rest.RestRequestLogic;
import ru.ilka.apartments.util.ContextHolder;

import java.util.*;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = WebTestConfig.class, loader = AnnotationConfigContextLoader.class)
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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(WebTestConfig.class);
        ContextHolder.setApplicationContext(applicationContext);
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
        javax.servlet.http.HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        request = new RestRequest(httpServletRequest);
    }

    @Test
    public void checkIfParametersAreCorrect(){
        Mockito.when(request.getMethod()).thenReturn(method);
        Mockito.when(request.getPathInfo()).thenReturn(pathInfo);
        Mockito.when(request.getParameter(COMMAND_PARAM)).thenReturn(expectedCommandType.toString());
        Assert.assertEquals(expectedCommandType,CommandType.valueOf(restRequestLogic.checkCommand(request).getParameter(COMMAND_PARAM)));
    }
}
