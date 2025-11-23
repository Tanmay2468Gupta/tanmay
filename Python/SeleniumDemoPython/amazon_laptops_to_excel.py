from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import pandas as pd

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

# Collect first 20 products (can increase)
products = driver.find_elements(By.XPATH, "//div[@data-component-type='s-search-result']")[:20]

# Prepare data
data = []
for product in products:
    try:
        title = product.find_element(By.TAG_NAME, "h2").text
    except:
        title = "No title found"
    try:
        price_whole = product.find_element(By.CSS_SELECTOR, ".a-price-whole").text
        price_fraction = product.find_element(By.CSS_SELECTOR, ".a-price-fraction").text
        price = f"₹{price_whole}.{price_fraction}"
    except:
        price = "Price not available"
    data.append([title, price])
    print(f"{title} - {price}")

# Save to Excel
df = pd.DataFrame(data, columns=["Title", "Price"])
df.to_excel("amazon_laptops.xlsx", index=False)
print("Data saved to amazon_laptops.xlsx")

# Close browser
driver.quit()

