// import org.openqa.selenium.*;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import java.io.FileOutputStream;
// import java.util.List;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import java.time.Duration;

// public class MakeMyTripAutomation {
//     public static void main(String[] args) throws Exception {

//         // ✅ Set ChromeDriver path
//         System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

//         // Setup Chrome
//         ChromeOptions options = new ChromeOptions();
//         options.addArguments("--start-maximized");
//         WebDriver driver = new ChromeDriver(options);
//         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//         try {
//             driver.get("https://www.makemytrip.com/");

//             // Close popups if appear
//             try {
//                 WebElement overlay = driver.findElement(By.cssSelector("body"));
//                 overlay.click();
//             } catch (Exception ignored) {}

//             // From city
//             WebElement fromCity = driver.findElement(By.id("fromCity"));
//             fromCity.click();
//             WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='From']"));
//             fromInput.sendKeys("Ahmedabad");
//             Thread.sleep(1000);
//             driver.findElement(By.xpath("//p[contains(text(),'Ahmedabad, India')]")).click();

//             // To city
//             WebElement toCity = driver.findElement(By.id("toCity"));
//             toCity.click();
//             WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
//             toInput.sendKeys("Hyderabad");
//             Thread.sleep(1000);
//             driver.findElement(By.xpath("//p[contains(text(),'Hyderabad, India')]")).click();

//             // Click search
//             driver.findElement(By.xpath("//a[contains(@class,'primaryBtn')]")).click();
//             Thread.sleep(5000);

//             // Extract flights
//             List<WebElement> flightNames = driver.findElements(By.xpath("//span[@class='boldFont blackText airlineName']"));
//             List<WebElement> flightPrices = driver.findElements(By.xpath("//p[@class='blackText fontSize18 blackFont white-space-no-wrap']"));

//             // Save to Excel
//             Workbook workbook = new XSSFWorkbook();
//             Sheet sheet = workbook.createSheet("Flights");
//             Row header = sheet.createRow(0);
//             header.createCell(0).setCellValue("Flight Name");
//             header.createCell(1).setCellValue("Price");

//             for (int i = 0; i < Math.min(flightNames.size(), flightPrices.size()); i++) {
//                 Row row = sheet.createRow(i + 1);
//                 row.createCell(0).setCellValue(flightNames.get(i).getText());
//                 row.createCell(1).setCellValue(flightPrices.get(i).getText());
//             }

//             FileOutputStream fileOut = new FileOutputStream("output.xlsx");
//             workbook.write(fileOut);
//             fileOut.close();
//             workbook.close();

//             System.out.println("✅ Flights saved successfully in output.xlsx");
//         } finally {
//             driver.quit();
//         }
//     }
// }
