package nextstep.jwp.ui;

import org.apache.coyote.http11.ContentType;
import org.apache.coyote.http11.HttpMethod;
import org.apache.coyote.http11.HttpStatus;
import org.apache.coyote.http11.header.HttpHeaders;
import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.request.mapping.controller.Controller;
import org.apache.coyote.http11.request.mapping.controllerscan.ControllerScan;
import org.apache.coyote.http11.request.mapping.controllerscan.RequestMapping;
import org.apache.coyote.http11.response.HttpResponse;

@ControllerScan
public class HomeController implements Controller {

    @Override
    @RequestMapping(method = HttpMethod.GET, uri = "/")
    public HttpResponse handle(final HttpRequest httpRequest) {
        return new HttpResponse(ContentType.TEXT_HTML, HttpStatus.OK, HttpHeaders.empty(), "Hello world!");
    }
}
