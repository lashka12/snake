package utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Game;

/*
 * this class will write the games history to JSON file
 */
public class JsonWriter {
	
	public static void writeGameHistory(Game gameToAdd) throws ParseException {
		
			        JSONObject gamesJson = new JSONObject();
			        JSONObject gameDetails = new JSONObject();
			        
			        String nickName = gameToAdd.getNickName().toString();
			        String score = String.valueOf(gameToAdd.getScore());
			        String duaration = Double.toString(gameToAdd.getDuration());

			        Date date = gameToAdd.getDate();
			        String pattern = "dd/MM/yyyy";
			        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			        String gameDate = simpleDateFormat.format(date);
			        
			        ArrayList<String> arr = new ArrayList<>();
			        HashMap<String, Integer> map = gameToAdd.getEatenObjects();
			        for (Entry<String, Integer> mapElement : map.entrySet()) { 
			        	arr.add(mapElement.getKey()+"#"+mapElement.getValue());
			        }
	
			        gameDetails.put("playerName",nickName );
			        gameDetails.put("date", gameDate);
			        gameDetails.put("score",score );
			        gameDetails.put("duaration",duaration );
			        gameDetails.put("history", arr);
		        
			        
	
					//Write JSON file
			        try {
			 
			        	Object obj = new JSONParser().parse(new FileReader("games.json"));
						JSONObject jo = (JSONObject) obj;
						JSONArray array = (JSONArray) jo.get("games");
						array.add(gameDetails);
				        gamesJson.put("games", array);
						FileWriter file = new FileWriter("games.json" , false);
			        	file.write(gamesJson.toJSONString());
			            file.flush();
			 
			        } catch (IOException e) {
			            e.printStackTrace();
			        }

		
	}

}
