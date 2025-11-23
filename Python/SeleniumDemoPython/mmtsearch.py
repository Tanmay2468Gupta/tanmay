from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.chrome.options import Options
import time

# Setup Chrome
options = Options()
options.add_argument("--start-maximized")
options.add_argument("--disable-notifications")
driver = webdriver.Chrome(options=options)

# Open MakeMyTrip
driver.get("https://www.makemytrip.com/")
time.sleep(5)

# Close login popup by clicking outside
ActionChains(driver).move_by_offset(10, 10).click().perform()
time.sleep(1)

# Close any additional modal popup if present
try:
    close_popup = driver.find_element(By.XPATH, "//span[@class='commonModal__close']")
    close_popup.click()
    print("Closed extra popup.")
except:
    pass

# Click on 'From' input
from_city = driver.find_element(By.ID, "fromCity")
from_city.click()
time.sleep(1)

# Type Kolkata and select suggestion
from_input = driver.find_element(By.XPATH, "//input[@placeholder='From']")
from_input.send_keys("Kolkata")
time.sleep(2)
driver.find_element(By.XPATH, "//p[contains(text(),'Kolkata, India')]").click()

# Click on 'To' input
to_city = driver.find_element(By.ID, "toCity")
to_city.click()
time.sleep(1)

# Type delhi and select suggestion
to_input = driver.find_element(By.XPATH, "//input[@placeholder='To']")
to_input.send_keys("Delhi")
time.sleep(2)
driver.find_element(By.XPATH, "//p[contains(text(),'Bengaluru, India')]").click()

# Select departure date (today + 5 days, for example)
time.sleep(2)
driver.find_element(By.XPATH, "//div[@aria-label='Sat Oct 25 2025']").click()  # adjust date if needed

# Click Search button
time.sleep(1)
driver.find_element(By.XPATH, "//a[text()='Search']").click()

print("✅ Searching flights from Kolkata → Bengaluru...")
time.sleep(10)  # wait for flight results to load

driver.quit()
