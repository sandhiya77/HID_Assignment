package API;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import  io.restassured.response.Response;



public class APITest {
	
	@Test
	public void ApiCall() {
		Map<String, Integer> hm = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		Response response=get("https://coinmap.org/api/v1/venues/");
		int actualStatus=response.getStatusCode();
		Assert.assertEquals(actualStatus, 200);
		List<String> GeoLocation=response.jsonPath().getList("venues.findAll{i->i.category == 'food'}.geolocation_degrees");
		List<String> name=response.jsonPath().getList("venues.findAll{i->i.category == 'food'}.name");
		System.out.println("names of food category "+name+"");
		System.out.println("GeoLocation of food category "+GeoLocation+"");
		List<String> category=response.jsonPath().getList("venues.category");
		for(int i=0;i<category.size();i++) {
			if(hm.containsKey(category.get(i))) {
				hm.put(category.get(i), hm.get(category.get(i))+1);
			}
			else {
				hm.put(category.get(i), 1);
			}
		}		
		System.out.println("Count of the categories in the venues "+hm+"");
	}

}
