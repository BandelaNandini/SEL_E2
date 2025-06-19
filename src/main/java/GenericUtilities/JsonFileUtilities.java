package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileUtilities {

	public String fetchDataFromJsonFile
	(String key) throws FileNotFoundException, IOException, ParseException {
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader("./src/test/resources/Vtiger_CommonData.json"));

		JSONObject json = (JSONObject) obj;
		String data = (String) json.get(key);

		return data;
	}

}
