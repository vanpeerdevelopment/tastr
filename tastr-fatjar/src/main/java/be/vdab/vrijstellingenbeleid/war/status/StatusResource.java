package be.vdab.vrijstellingenbeleid.war.status;

import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(StatusResource.STATUS_URL)
public class StatusResource {

    public static final String STATUS_URL = "/status";

    @Timed
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public String status(){
        return "up";
    }
}
