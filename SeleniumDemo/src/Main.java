// package SeleniumDemo;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;

// public class Main {
//     public static void main(String[] args) {
//         // Set path to ChromeDriver
//         System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

//         // Launch Chrome
//         WebDriver driver = new ChromeDriver();

//         // Open Google
//         driver.get("https://www.google.com");

//         // Search something
//         WebElement searchBox = driver.findElement(By.name("q"));
//         searchBox.sendKeys("Selenium WebDriver Java");
//         searchBox.submit();

//         // Wait a bit
//         try {
//             Thread.sleep(6000);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }

//         // Close browser
//         driver.quit();
//     }
// }
