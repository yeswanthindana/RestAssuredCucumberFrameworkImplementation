package stepdefinitions;

import io.cucumber.java.Before;
import resources.utilities;

import java.io.IOException;

import static stepdefinitions.stepdefs.actplace_id;

public class hooks extends utilities{

    @Before("@deletePlace")
    public void beforedelete() throws IOException {

        if(actplace_id == null){

            stepdefs sd = new stepdefs();
            sd.request_payload("london, USA");
            sd.user_calls_with_https_request("addPlaceAPI","post");
            sd.verify_in_should_be_equal_from_response("Frontline house Vizag","French-IN","89, Klinkara side layout, cohen 09","getPlaceAPI");



        }
    }
}
