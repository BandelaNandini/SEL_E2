package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author B.Nandini
 * This class is created to access property file
 */
public class PropertyFileUtility {

	/**
	 * This method is used to fetch data from property file
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String fetchDataFromPropFile(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;

	}

	/**
	 * This method is used to write back the data to property file
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void writeBackDataToPropFile(String key, String value) throws Exception {
		FileInputStream fis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		p.put(key, value);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/CommonData.properties");
		p.store(fos, "Updated successfully");

	}
}
