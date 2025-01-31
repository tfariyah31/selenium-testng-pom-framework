package Tfar.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int retryCount=0;
	static final int maxRetryCount=2;;
	
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}

}
