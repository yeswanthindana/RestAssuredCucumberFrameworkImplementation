package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class utilities {


    public static RequestSpecification req;
    public static ResponseSpecification res;
    static PrintStream stream;
    static FileOutputStream fs;
    static FileInputStream fi;
    static Properties prop;
    static String timestamp ;
    public static JsonPath js;
    static String path = System.getProperty("user.dir") + "/src/test/java/resources/global.properties";



    public RequestSpecification requestSpecification() throws IOException {



        timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
     //   String filename = "Payload_" + timestamp + ".txt";

        if (req == null){ //Uncomment if you all logs in a single file
        fs = new FileOutputStream(System.getProperty("user.dir")+"\\Logs\\Logging"+timestamp+".txt");
        stream = new PrintStream(fs);
        req = new RequestSpecBuilder()
                .addFilter(RequestLoggingFilter.logRequestTo(stream))
                .addFilter(ResponseLoggingFilter.logResponseTo(stream))
             //   .setBaseUri(getGlobalValue("baseURL"))
                .setBaseUri(getGlobalValue("LibraryBaseURL"))
                .setContentType(ContentType.JSON)
                .build();

        return req;
        }  //Uncomment if you all logs in a single file

      return req; //Uncomment if you all logs in a single file

    }


    public ResponseSpecification responseSpecification()  {
        res = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
        return res;
    }

    public static String getGlobalValue(String key) throws IOException {

        fi = new FileInputStream(path);
        prop = new Properties();
        prop.load(fi);
        return prop.getProperty(key);

    }

    public static String getSONPath(Response response, String key ){

            String resp = response.asString();
            if (resp == null || resp.trim().isEmpty()) {
                throw new IllegalArgumentException("Response body is null or empty");
            }
            JsonPath js = new JsonPath(resp);
            return js.get(key);

    }

    public static void print(Object key){

        System.out.println(key);
    }
}
