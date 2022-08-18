package JSONObject_API_Spotify_Music.JSONObject_API_Spotify_Music;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JsonObject_Spotify {
String token;
String user_id;
String playlist_id;

@BeforeTest
public void getToken() {
	token = "Bearer BQA-2uciXoGjvucvL6XGsI0Z_FswNvSaBN7eKAphnH8krFh927cKcgac-FLxh19djLnrqcKDoBy3_wEAKlcLjcpW3-NZ9eyTG7QWhyQkRZaK7sefk908rFY2C-wxXWPz9GE0x-OWX32mI47eOiHJT5B4axyzOa_7gfZrjHrVLM1c0wjnnwrQNQ88rVUJkj-qzmv9Cn80moMp3RoQRoxiIijpjviPqjlCyO4nveCWTtMNALC-ovTn5SQ7Vg6yLc3l84BXSHfppfg";
}

	// --------------------------------- Users Profile --------------------------------\\	

@Test (priority=1)
public void Get_Current_Users_Profile() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.accept(ContentType.JSON);
		requestSpecification.contentType("application/json");
		requestSpecification.header("Authorization", token);
		Response response = requestSpecification.get("https://api.spotify.com/v1/me");
		System.out.println("Response code: " + response.getStatusCode());
		user_id = response.path("id");
		System.out.println("My user ID:" + user_id);
		response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test (priority=2)
public void Get_Users_Profile() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.accept(ContentType.JSON);
		requestSpecification.contentType("application/json");
		requestSpecification.header("Authorization", token);
		Response response = requestSpecification.get("https://api.spotify.com/v1/users/"+user_id+"/");
		System.out.println("Response code: " + response.getStatusCode());
		response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

		// --------------------------------- PlayList --------------------------------\\

@Test (priority=3)
public void Create_Playlist1() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.accept(ContentType.JSON);
		requestSpecification.contentType("application/json");
		requestSpecification.header("Authorization", token);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "CFP-176");
		jsonObject.put("description", "new playlist description");
		jsonObject.put("public", "false");
		requestSpecification.body(jsonObject.toJSONString());
		Response response = requestSpecification.post("https://api.spotify.com/v1/users/"+user_id+"/playlists");
		System.out.println("Response code: " + response.getStatusCode());
		playlist_id = response.path("id");
		System.out.println("My Playlist ID :" + playlist_id);
		response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 201);
}

@Test (priority=4)
public void Add_Items_to_Playlist() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.accept(ContentType.JSON);
		requestSpecification.contentType("application/json");
		requestSpecification.header("Authorization", token);
		requestSpecification.queryParam("uris", "spotify:track:16XEVyPh5NT31CAAqPbxQF,spotify:track:6rkqqLPg9Lbsdh26JMqfp0,spotify:track:6ZRzF7XiZdjnQUDYIk7w7u,spotify:track:7fyIuR4aaWb6iltlAoSkxF,spotify:track:1cpaDNciPGlC39qPs4RkMU");
		Response response = requestSpecification.post("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
		response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 201);
}

@Test(priority = 5)
public void GetPlaylist() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);
	Response response = requestSpecification.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/");
	response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 6)
public void RemovePlaylistItem() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);
	requestSpecification.body("{ \"tracks\": [{ \"uri\": \"spotify:track:16XEVyPh5NT31CAAqPbxQF\" }] }");
	Response response = requestSpecification.delete("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");
	response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 7)
public void GetUserPlaylist() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	Response response = requestSpecification.get("https://api.spotify.com/v1/users/"+user_id+"/playlists");
	response.prettyPrint();

	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 8)
public void Get_Playlist() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	Response response = requestSpecification.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/");
	response.prettyPrint();

	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 9)
public void GetPlaylistItems() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	Response response = requestSpecification.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");

	response.prettyPrint();

	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 10)

public void GetPlaylistCoverImage() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	Response response = requestSpecification.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");

	response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 11)

public void GetCurrentUserPlaylists() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	Response response = requestSpecification.get("https://api.spotify.com/v1/me/playlists");

	response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 12)

public void AddCustomPlaylistCoverImage() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	Response response = requestSpecification.get("https://api.spotify.com/v1/playlists/"+playlist_id+"/images");

	response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 10)
public void UpdatePlaylistItems() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	requestSpecification.body("{\r\n" + "  \"range_start\": 1,\r\n" + "  \"insert_before\": 3,\r\n"
			+ "  \"range_length\": 2\r\n" + "}");

	Response response = requestSpecification.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/tracks");

	response.prettyPrint();

	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

@Test(priority = 11)
public void ChangePlaylistDetails() {
	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);

	requestSpecification.body("{\r\n" + "  \"name\": \"Updated Playlist Name\",\r\n"
			+ "  \"description\": \"Updated playlist description\",\r\n" + "  \"public\": false\r\n" + "}");

	Response response = requestSpecification.put("https://api.spotify.com/v1/playlists/"+playlist_id+"/");

	response.prettyPrint();

	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);
}

				//--------------------------------- Search --------------------------------\\


@Test (priority = 12)
public void SearchForItem() {

	RequestSpecification requestSpecification = RestAssured.given();
	requestSpecification.accept(ContentType.JSON);
	requestSpecification.contentType("application/json");
	requestSpecification.header("Authorization", token);
	requestSpecification.queryParam("q", "Arijit singh");
	requestSpecification.queryParam("type", "track"	);

	Response response = requestSpecification.get("https://api.spotify.com/v1/search");

	response.prettyPrint();
	int statusCode = response.statusCode();
	Assert.assertEquals(statusCode, 200);

}	 



}
