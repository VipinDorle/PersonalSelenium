package LoginFunctionality;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoLogin {
	static WebDriver driver = new ChromeDriver();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver.manage().window().maximize();
		driver.get("http://demo.seleniumeasy.com/basic-first-form-demo.html");
		
		LoginPersonal();
		
	}
	static void LoginPersonal(){
		
	}
}
