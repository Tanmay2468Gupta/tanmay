from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

# Setup Chrome
options = Options()
options.add_argument("--start-maximized")
options.add_argument("--disable-notifications")
driver = webdriver.Chrome(options=options)

driver.get("https://www.makemytrip.com/")
wait = WebDriverWait(driver, 20)

time.sleep(5)  # wait for page animations

# Close login/signup popup by clicking empty space
ActionChains(driver).move_by_offset(10, 10).click().perform()
time.sleep(1)

# Close any ad or notification popup if present
try:
    driver.find_element(By.XPATH, "//span[@class='commonModal__close']").click()
except:
    pass

# === FROM CITY ===
from_city = wait.until(EC.element_to_be_clickable((By.ID, "fromCity")))
driver.execute_script("arguments[0].click();", from_city)
time.sleep(1)

from_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@placeholder='From']")))
from_input.send_keys("Kolkata")
time.sleep(2)
driver.find_element(By.XPATH, "//p[contains(text(),'Kolkata, India')]").click()

# Small wait to let the destination box become active
time.sleep(2)

# === TO CITY ===
# use JS to click the 'To' field (normal click won't work reliably)
to_city = wait.until(EC.presence_of_element_located((By.ID, "toCity")))
driver.execute_script("arguments[0].click();", to_city)
time.sleep(1)

# now find the active "To" input (visible, empty value)
to_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='To' and not(@value)]")))
to_input.send_keys("Delhi")
time.sleep(2)

# Select Delhi from dropdown
driver.find_element(By.XPATH, "//p[contains(text(),'Delhi, India')]").click()

# === SELECT DATE ===
time.sleep(2)
driver.find_element(By.XPATH, "(//div[@aria-label])[10]").click()

# === SEARCH ===
wait.until(EC.element_to_be_clickable((By.XPATH, "//a[text()='Search']"))).click()

print("✅ Searching flights from Kolkata → Delhi...")
time.sleep(10)

driver.quit()
