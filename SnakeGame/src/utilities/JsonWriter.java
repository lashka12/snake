package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import model.Game;

/*
 * this class will write the games history to JSON file
 */
public class JsonWriter {
	
	public static void writeGameHistory(ArrayList<Game> gamesHistory) {
		
					JSONArray array = new JSONArray();
			        JSONObject gameDetails = new JSONObject();
			        JSONObject gamesJson = new JSONObject();
			        
			        for(Game gameToAdd : gamesHistory ) {
			        	if(gameToAdd != null ) {
			        		
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
					        try(FileWriter file = new FileWriter("games.json")) {
					 
								array.add(gameDetails);
						        gamesJson.put("games", array);
					        	file.write(gamesJson.toJSONString());
					            file.flush();
					 
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
				      	
			        	}

			        }
	
	}

}
