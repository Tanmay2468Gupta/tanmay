from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import time

# Setup Chrome driver
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

# Open Amazon
driver.get("https://www.amazon.in/")  # Change region if needed

# Wait for the search box to be present
wait = WebDriverWait(driver, 10)
search_box = wait.until(EC.presence_of_element_located((By.ID, "twotabsearchtextbox")))

# Search for "laptop"
search_box.send_keys("laptop")
search_box.submit()

# Wait for search results
wait.until(EC.presence_of_all_elements_located((By.XPATH, "//div[@data-component-type='s-search-result']")))

# Get first 5 product titles and prices
products = driver.find_elements(By.XPATH, "//div[@data-component-type='s-search-result']")[:5]

for index, product in enumerate(products, start=1):
    try:
        title = product.find_element(By.TAG_NAME, "h2").text
    except:
        title = "No title found"
    try:
        price_whole = product.find_element(By.CSS_SELECTOR, ".a-price-whole").text
        price_fraction = product.find_element(By.CSS_SELECTOR, ".a-price-fraction").text
        price = f"₹{price_whole}.{price_fraction}"
    except:
        price = "No price found"
    print(f"{index}. {title} - {price}")

# Close browser
driver.quit()
