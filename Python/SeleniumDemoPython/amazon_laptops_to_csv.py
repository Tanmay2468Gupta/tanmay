from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import csv

# Setup Chrome driver
driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))

# Open Amazon India
driver.get("https://www.amazon.in/")

# Wait for the search box
wait = WebDriverWait(driver, 10)
search_box = wait.until(EC.presence_of_element_located((By.ID, "twotabsearchtextbox")))

# Search for "laptop"
search_box.send_keys("laptop")
search_box.submit()

# Wait for search results
wait.until(EC.presence_of_all_elements_located((By.XPATH, "//div[@data-component-type='s-search-result']")))

# Collect all search results (first 20 for example)
products = driver.find_elements(By.XPATH, "//div[@data-component-type='s-search-result']")[:20]

# Prepare CSV file
with open("amazon_laptops.csv", "w", newline="", encoding="utf-8") as file:
    writer = csv.writer(file)
    writer.writerow(["Title", "Price"])

    for product in products:
        # Get product title
        try:
            title = product.find_element(By.TAG_NAME, "h2").text
        except:
            title = "No title found"

        # Get product price
        try:
            price_whole = product.find_element(By.CSS_SELECTOR, ".a-price-whole").text
            price_fraction = product.find_element(By.CSS_SELECTOR, ".a-price-fraction").text
            price = f"₹{price_whole}.{price_fraction}"
        except:
            price = "Price not available"

        # Write to CSV
        writer.writerow([title, price])
        print(f"{title} - {price}")

# Close browser
driver.quit()
print("Data saved to amazon_laptops.csv")
