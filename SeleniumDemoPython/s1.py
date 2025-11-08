from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support import expected_conditions as EC
import pandas as pd
import time

service = Service(r"C:\Users\Subham\Downloads\chromedriver-win64\chromedriver-win64\chromedriver.exe")
options = Options()
options.add_argument("--start-maximized")

driver = webdriver.Chrome(service=service, options=options)
wait = WebDriverWait(driver, 20)  # wait max 20 sec for elements

try:
    driver.get("https://www.makemytrip.com/")
    time.sleep(5)

    # Try closing popups or ads
    try:
        driver.find_element(By.CSS_SELECTOR, "body").click()
        print("Main popup closed")
    except:
        print("No main popup found")

    try:
        ad_close = wait.until(EC.element_to_be_clickable((By.CSS_SELECTOR, "span.commonModal__close")))
        ad_close.click()
        print("Ad popup closed")
    except:
        print("No ad popup found")
    try:
    # 1. Click the 'From' container using JavaScript (most reliable way to open it)
        from_city_container = wait.until(EC.presence_of_element_located((By.ID, "fromCity")))
        driver.execute_script("arguments[0].click();", from_city_container)

        # 2. Target the specific active input field using its data attribute
        from_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@data-cy='origin']")))
        from_input.clear() # Good practice to clear before typing
        from_input.send_keys("Ahmedabad")
        time.sleep(1) # Wait briefly for suggestions to populate

        # 3. Instead of clicking the suggestion text, hit ENTER to select the top suggestion
        from_input.send_keys(Keys.ENTER)
        
        # 4. A small wait to ensure the selection registers before moving to 'To' city
        time.sleep(1)
        print("✅ From city selected successfully (via Keys.ENTER)")

    except Exception as e:
        print(f"❌ From city selection failed: {e}")

    # To city (Fix for not typing)
    try:
    # Sometimes, after selecting From city, To city box is hidden — click the area again
        to_city_box = wait.until(EC.element_to_be_clickable((By.XPATH, "//label[@for='toCity']")))
        to_city_box.click()
        time.sleep(1)

        to_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@placeholder='To']")))
        to_input.clear()
        to_input.send_keys("Hyderabad")

        wait.until(EC.element_to_be_clickable((By.XPATH, "//p[contains(text(),'Hyderabad, India')]"))).click()
        print("✅ To city selected successfully")

    except Exception as e:
        print(f"❌ To city selection failed: {e}")


    # Pick a date (closes the calendar popup)
    try:
        date = wait.until(EC.element_to_be_clickable((By.XPATH, "//div[@aria-label='Fri Nov 21 2025']")))
        date.click()
        print("✅ Date selected successfully")
    except:
        print("⚠️ Could not select date, maybe already selected")

    # Click Search button
    try:
        search_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//a[contains(@class,'primaryBtn')]")))
        driver.execute_script("arguments[0].click();", search_button)  # use JS click to avoid interception
        print("✅ Search button clicked successfully")
    except Exception as e:
        print(f"❌ Search click failed: {e}")

    # Wait for results
    time.sleep(10)

    # Get flight details
    flight_names = driver.find_elements(By.XPATH, "//span[@class='boldFont blackText airlineName']")
    flight_prices = driver.find_elements(By.XPATH, "//p[@class='blackText fontSize18 blackFont white-space-no-wrap']")

    names = [f.text for f in flight_names]
    prices = [p.text for p in flight_prices]

    # Save to Excel
    df = pd.DataFrame({"Flight Name": names, "Price": prices})
    df.to_excel("flights.xlsx", index=False)
    print("✅ Flights saved successfully in flights.xlsx")

except Exception as e:
    print(f"❌ Error: {e}")

finally:
    driver.quit()
