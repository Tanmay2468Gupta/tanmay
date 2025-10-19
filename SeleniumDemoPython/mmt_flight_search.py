from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from datetime import datetime, timedelta
import time

# --- SETUP CHROME DRIVER ---
service = Service()
driver = webdriver.Chrome(service=service)
driver.maximize_window()
wait = WebDriverWait(driver, 60)

# --- OPEN MAKE MY TRIP ---
driver.get("https://www.makemytrip.com/")
time.sleep(5)

# --- CLOSE POPUPS ---
for selector in [
    "span.commonModal__close",
    "button[data-cy='closeModal']",
    "span#webklipper-publisher-widget-container-notification-close-div"
]:
    try:
        driver.find_element(By.CSS_SELECTOR, selector).click()
        time.sleep(1)
    except:
        pass

# --- CLICK FLIGHTS TAB ---
try:
    flights_tab = wait.until(EC.element_to_be_clickable((By.XPATH, "//span[text()='Flights']")))
    flights_tab.click()
except:
    pass
time.sleep(2)

# --- FROM CITY (Kolkata) ---
from_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//label[@for='fromCity']")))
from_input.click()
from_box = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@placeholder='From']")))
from_box.send_keys("Kolkata")
time.sleep(1)
from_box.send_keys(Keys.ENTER)

# --- TO CITY (Delhi) ---
to_input = wait.until(EC.element_to_be_clickable((By.XPATH, "//label[@for='toCity']")))
to_input.click()
to_box = wait.until(EC.element_to_be_clickable((By.XPATH, "//input[@placeholder='To']")))
to_box.send_keys("Delhi")
time.sleep(1)
to_box.send_keys(Keys.ENTER)

# --- SELECT TOMORROW’S DATE ---
tomorrow = datetime.now() + timedelta(days=1)
day = tomorrow.day
month_year = tomorrow.strftime("%B %Y")

wait.until(EC.element_to_be_clickable((By.XPATH, "//label[@for='departure']"))).click()
time.sleep(2)

while True:
    month_element = driver.find_element(By.XPATH, "(//div[@class='DayPicker-Caption'])[1]/div").text
    if month_year in month_element:
        break
    else:
        driver.find_element(By.XPATH, "//span[@aria-label='Next Month']").click()
        time.sleep(1)

xpath_day = f"//div[@class='DayPicker-Day' and not(contains(@class,'disabled'))]/div/p[text()='{day}']"
driver.find_element(By.XPATH, xpath_day).click()

# --- CLICK SEARCH ---
search_button = driver.find_element(By.XPATH, "//a[text()='Search']")
search_button.click()
print("✈️ Searching flights from Kolkata to Delhi for tomorrow...")

# --- WAIT FOR URL TO CHANGE ---
wait.until(EC.url_contains("/flight/search"))
print("✅ Redirected to search results page.")

# --- WAIT FOR RESULTS TO LOAD FULLY ---
print("⏳ Waiting for flight results to appear...")

try:
    # Scroll down gradually to trigger JS rendering
    for _ in range(10):
        driver.execute_script("window.scrollBy(0, 500);")
        time.sleep(1)

    # Wait until at least one result card appears
    results = wait.until(EC.presence_of_all_elements_located((By.XPATH, "//div[contains(@class,'listingCard')]")))
    print(f"✅ {len(results)} flights found.")
except Exception as e:
    print("⚠️ Results still loading or blocked by overlay.")
    print("Error:", e)

time.sleep(5)
driver.quit()
