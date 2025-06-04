package resources;

import com.google.gson.Gson;
import pojo.addPlace;
import pojo.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class testdatabuild {
    public static addPlace sp;
    public static location spl;

    public static addPlace addPlacepayload(){

        sp = new addPlace();
        sp.setAccuracy(70);
        sp.setAddress("89, Klinkara side layout, cohen 09");
        sp.setPhone_number("(+91) 984 896 3947");
        sp.setName("Frontline house Vizag");
        sp.setWebsite("http://fbook.com");
        sp.setLanguage("French-IN");

        List<String> al = new ArrayList<String>();
        al.add("shot park");
        al.add("belt shop");

        sp.setTypes(al);


        spl = new location();
        spl.setLat(-38.389494);
        spl.setLng(33.429362);

        sp.setLocation(spl);

        return sp;

    }

    public static addPlace addPlacepayloadwithexamples(String name, String language, String address){

        sp = new addPlace();
        sp.setAccuracy(70);
        sp.setAddress(address);
        sp.setPhone_number("(+91) 984 896 3947");
        sp.setName(name);
        sp.setWebsite("http://fbook.com");
        sp.setLanguage(language);

        List<String> al = new ArrayList<String>();
        al.add("shot park");
        al.add("belt shop");

        sp.setTypes(al);


        spl = new location();
        spl.setLat(-38.389494);
        spl.setLng(33.429362);

        sp.setLocation(spl);

        return sp;

    }

    public static String deletePlacePayload(String place_id) {
        String delbody = "{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}\n";
        return delbody;
    }

    public static String addLibraryBook(String name, String aisle, String author) throws Exception {

        excelUtilities ex = new excelUtilities();

        ArrayList<String> l = ex.getData(name,aisle, author);

            HashMap<String,Object> map   = new HashMap();
            System.out.println(l);
            map.put("name",l.get(1));
            map.put("isbn","bcd");
            map.put("aisle",l.get(2));
            map.put("author",l.get(3));
//            for nested JSON
//            HashMap<String,Object> map1 = new HashMap<String,Object>();
//            map1.put("lat", -38.389494);
//            map1.put("lng", 33.429362);
//            map.put("location", map1);
        return new Gson().toJson(map);
    }
}
