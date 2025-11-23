import time
import chromedriver_autoinstaller
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

# ✅ Auto install correct ChromeDriver for your Chrome v141
chromedriver_autoinstaller.install()

# ✅ Setup Chrome options
options = Options()
options.add_argument("--start-maximized")
options.add_argument("--disable-notifications")
options.add_argument("--disable-infobars")
options.add_argument("--disable-extensions")

# ✅ Launch Chrome
driver = webdriver.Chrome(options=options)
driver.implicitly_wait(10)

# ✅ Open MakeMyTrip
driver.get("https://www.makemytrip.com/")
wait = WebDriverWait(driver, 20)

time.sleep(5)  # wait for initial popups to appear

# === CLOSE LOGIN POPUP ===
ActionChains(driver).move_by_offset(10, 10).click().perform()
time.sleep(1)

# === CLOSE ANY AD POPUP IF PRESENT ===
try:
    driver.find_element(By.XPATH, "//span[@class='commonModal__close']").click()
except:
    pass

# === FROM CITY: KOLKATA ===
from_city = wait.until(EC.element_to_be_clickable((By.ID, "fromCity")))
driver.execute_script("arguments[0].click();", from_city)
time.sleep(1)

from_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='From']")))
from_input.send_keys("Kolkata")
time.sleep(2)
driver.find_element(By.XPATH, "//p[contains(text(),'Kolkata, India')]").click()

# Small pause so destination box becomes active
time.sleep(2)

# === TO CITY: DELHI ===
to_city = wait.until(EC.presence_of_element_located((By.ID, "toCity")))
driver.execute_script("arguments[0].click();", to_city)
time.sleep(1)

to_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='To' and not(@value)]")))
to_input.send_keys("Delhi")
time.sleep(2)
driver.find_element(By.XPATH, "//p[contains(text(),'Delhi, India')]").click()

# === SELECT DATE (any visible future date) ===
time.sleep(2)
driver.find_element(By.XPATH, "(//div[@aria-label])[10]").click()

# === CLICK SEARCH ===
wait.until(EC.element_to_be_clickable((By.XPATH, "//a[text()='Search']"))).click()

print("✅ Searching flights from Kolkata → Delhi...")

# Wait to let results load
time.sleep(10)

# ✅ Done
driver.quit()
