package ru.ilka.apartments.model.logic;

import org.springframework.stereotype.Component;
import ru.ilka.apartments.model.command.CommandType;
import ru.ilka.apartments.model.entity.RestRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
@Component
public class RestRequestLogic {
    private static final String COMMAND_PARAM = "command";
    private static final String PARAM_APARTMENT_ID = "apartmentId";
    private static final String PARAM_DURATION = "duration";

    private static final Pattern REGEX_SHOW_AVAILABLE = Pattern.compile("/apartments/available");
    private static final Pattern REGEX_ADD = Pattern.compile("/apartment");
    private static final Pattern REGEX_BOOK_ON_TIME_BY_ID = Pattern.compile("/apartment/([1-9]+[0-9]*)/days/([1-7]+)");
    private static final Pattern REGEX_ALL = Pattern.compile("/apartments/");
    private static final Pattern REGEX_BY_ID = Pattern.compile("/apartment/([1-9]+[0-9]*)");

    private static final String DEFAULT_DURATION = "2";

    public RestRequestLogic() {
    }

    public HttpServletRequest checkCommand(HttpServletRequest request) {
        String method = request.getMethod();

        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            RestRequest restRequest = new RestRequest(request);

            Matcher matcher;

            switch (method){
                case "GET":
                    matcher = REGEX_SHOW_AVAILABLE.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.SHOW_AVAILABLE.toString());
                        return restRequest;
                    }

                    matcher = REGEX_ALL.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.SHOW_ALL.toString());
                        return restRequest;
                    }

                    matcher = REGEX_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.SHOW_BY_ID.toString());
                        restRequest.addParameter(PARAM_APARTMENT_ID, findId(pathInfo));
                        return restRequest;
                    }
                    break;
                case "POST":
                    matcher = REGEX_ADD.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.ADD.toString());
                        return restRequest;
                    }
                    break;
                case "DELETE":
                    matcher = REGEX_ALL.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.DELETE_ALL.toString());
                        return restRequest;
                    }

                    matcher = REGEX_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.DELETE.toString());
                        restRequest.addParameter(PARAM_APARTMENT_ID, findId(pathInfo));
                        return restRequest;
                    }
                    break;
                case "PUT":
                    matcher = REGEX_BOOK_ON_TIME_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.BOOK.toString());
                        String[] path = pathInfo.split("/");
                        restRequest.addParameter(PARAM_APARTMENT_ID, path[path.length - 3]);
                        restRequest.addParameter(PARAM_DURATION, path[path.length - 1]);
                        return restRequest;
                    }

                    matcher = REGEX_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.BOOK.toString());
                        restRequest.addParameter(PARAM_APARTMENT_ID, findId(pathInfo));
                        restRequest.addParameter(PARAM_DURATION, DEFAULT_DURATION);
                        return restRequest;
                    }
                    break;
                default:
                    return request;
            }
        }
        return request;
    }

    private String findId(String str) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str);

        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }

        return stringBuilder.toString();
    }
}
