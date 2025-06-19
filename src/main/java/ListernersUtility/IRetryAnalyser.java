package ListernersUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyser implements IRetryAnalyzer {

	int max_retries = 5;
	int count = 0;

	@Override
	public boolean retry(ITestResult result) {

		if (count < max_retries) {
			count++;
			return true;
		}

		return false;
	}

}
