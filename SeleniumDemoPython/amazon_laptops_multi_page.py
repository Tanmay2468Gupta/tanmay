from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import pandas as pd
import time

# Setup Chrome driver
options = webdriver.ChromeOptions()
options.add_argument("--start-maximized")
# optional: run normally instead of incognito to avoid extra popups
# options.add_argument("--incognito")  

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
driver.get("https://www.flipkart.com/") // flipkart 

wait = WebDriverWait(driver, 10)

# Handle cookie / sign-in popup if it appears
try:
    continue_button = wait.until(EC.element_to_be_clickable((By.ID, "sp-cc-accept")))
    continue_button.click()
    print("Clicked cookie accept button.")
except:
    pass  # no popup appeared

# Wait for search box
search_box = wait.until(EC.presence_of_element_located((By.ID, "twotabsearchtextbox")))
search_box.send_keys("laptop")
search_box.submit()

# Wait for results
wait.until(EC.presence_of_all_elements_located((By.XPATH, "//div[@data-component-type='s-search-result']")))

# Now you can continue scraping multiple pages...


# Number of pages to scrape
num_pages = 5
all_data = []

for page in range(num_pages):
    print(f"Scraping page {page + 1}...")
    time.sleep(2)  # wait for page to load

    products = driver.find_elements(By.XPATH, "//div[@data-component-type='s-search-result']")

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
        all_data.append([title, price])
        print(f"{title} - {price}")

    # Try to go to the next page
    try:
        next_button = driver.find_element(By.XPATH, "//a[contains(@class,'s-pagination-next')]")
        driver.execute_script("arguments[0].click();", next_button)
        wait.until(EC.presence_of_all_elements_located((By.XPATH, "//div[@data-component-type='s-search-result']")))
    except:
        print("No more pages or Next button not found.")
        break

# Save all results to Excel
df = pd.DataFrame(all_data, columns=["Title", "Price"])
df.to_excel("amazon_laptops_multi_page.xlsx", index=False)
print("Data saved to amazon_laptops_multi_page.xlsx")

# Close browser
driver.quit()

