from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
import time

# Setup Chrome
options = Options()
options.add_argument("--start-maximized")
driver = webdriver.Chrome(options=options)

# Open MakeMyTrip
driver.get("https://www.makemytrip.com/")
time.sleep(5)  # wait for popups to appear

# Switch to main content (sometimes it's inside iframe)
driver.switch_to.default_content()

# Click somewhere empty to close the login popup
try:
    # Click on body to remove login/signup popup
    ActionChains(driver).move_by_offset(10, 10).click().perform()
    time.sleep(1)
except Exception as e:
    print("No main popup:", e)

# Close notification popup if it appears
try:
    close_btn = driver.find_element(By.XPATH, "//span[@class='commonModal__close']")
    close_btn.click()
    print("Closed notification popup")
except:
    print("No notification popup found")

# Close login modal if still visible
try:
    login_popup = driver.find_element(By.XPATH, "//span[@class='commonModal__close']")
    login_popup.click()
    print("Closed login popup")
except:
    print("No login popup found")

# Wait a bit before ending
time.sleep(3)

print("✅ All popups handled successfully!")

driver.quit()
