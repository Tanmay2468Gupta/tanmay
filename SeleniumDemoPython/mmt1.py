from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from datetime import datetime, timedelta
from openpyxl import Workbook
import time

# --- SETUP CHROME DRIVER ---
service = Service()
driver = webdriver.Chrome(service=service)
driver.maximize_window()
wait = WebDriverWait(driver, 60)

# --- OPEN MAKE MY TRIP ---
driver.get("https://www.makemytrip.com/")
time.sleep(5)
driver.find_element(By.TAG_NAME, "body").click()  # Dismiss login modal

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
    ActionChains(driver).move_to_element(flights_tab).click().perform()
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

xpath_day = f"//div[@class='DayPicker-Day']/div/p[text()='{day}']"
driver.find_element(By.XPATH, xpath_day).click()

# --- CLICK SEARCH ---
search_button = driver.find_element(By.XPATH, "//a[text()='Search']")
search_button.click()
print("✈️ Searching flights from Kolkata to Delhi for tomorrow...")

# --- WAIT FOR URL TO CHANGE ---
wait.until(EC.url_contains("/flight/search"))
print("✅ Redirected to search results page.")

# --- WAIT FOR RESULTS TO LOAD ---
print("⏳ Waiting for flight results to appear...")
time.sleep(10)

# Scroll to bottom to trigger lazy loading
driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
time.sleep(5)

# --- EXTRACT FLIGHT NAMES & PRICES ---
flights_data = []
try:
    flight_cards = wait.until(EC.presence_of_all_elements_located((By.XPATH, "//div[contains(@class,'listingCard')]")))
    print(f"✅ Found {len(flight_cards)} flights. Extracting data...")

    for card in flight_cards:
        try:
            name = card.find_element(By.XPATH, ".//p[contains(@class,'airlineName')]").text
        except:
            name = "N/A"
        try:
            time_text = card.find_element(By.XPATH, ".//div[@class='flexOne timeInfoLeft']/p").text
        except:
            time_text = "N/A"
        try:
            price = card.find_element(By.XPATH, ".//div[contains(@class,'priceSection')]/p").text
        except:
            price = "N/A"

        flights_data.append([name, time_text, price])
except Exception as e:
    print("⚠️ Error extracting flight data:", e)

# --- SAVE RESULTS TO EXCEL ---
if flights_data:
    wb = Workbook()
    ws = wb.active
    ws.title = "Flight Results"
    ws.append(["Airline", "Departure Time", "Price"])
    for row in flights_data:
        ws.append(row)
    filename = f"Flights_Kolkata_to_Delhi_{tomorrow.strftime('%d_%m_%Y')}.xlsx"
    wb.save(filename)
    print(f"📁 Data saved successfully in '{filename}'")
else:
    print("⚠️ No flight data extracted.")

time.sleep(5)
driver.quit()