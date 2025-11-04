import time
import pandas as pd
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException, NoSuchElementException

# Import and setup webdriver
try:
    from webdriver_manager.chrome import ChromeDriverManager
    options = webdriver.ChromeOptions()
    options.add_argument("--start-maximized")
    options.add_argument("--disable-notifications")
    driver = webdriver.Chrome(service=webdriver.ChromeService(ChromeDriverManager().install()), options=options)
except ImportError:
    print("WebDriverManager not found. Please install it with 'pip install webdriver-manager'")
    print("Falling back to default webdriver. Make sure chromedriver is in your PATH.")
    driver = webdriver.Chrome() # Assumes chromedriver is in your system'S PATH

# Set explicit wait time
wait = WebDriverWait(driver, 15)

def handle_initial_popup():
    """Handles the initial ad/login modal popup."""
    try:
        # Wait for the modal to be visible. This selector targets the close button of the iframe modal.
        close_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//span[contains(@class, 'logSprite') and contains(@class, 'icClose')]")))
        close_button.click()
        print("Closed initial modal popup.")
    except TimeoutException:
        print("No initial modal popup found or it timed out.")
    except Exception as e:
        print(f"An error occurred while trying to close the initial popup: {e}")

def search_flights(from_city, to_city):
    """Performs the flight search on MakeMyTrip."""
    try:
        # --- FROM CITY ---
        # Click on the "From" field to open the input
        from_field = wait.until(EC.element_to_be_clickable((By.ID, "fromCity")))
        from_field.click()
        
        # Enter the city name in the input
        from_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='From']")))
        from_input.send_keys(from_city)
        time.sleep(1) # Small delay for autosuggest
        
        # Click the first suggestion
        first_suggestion = wait.until(EC.element_to_be_clickable((By.ID, "react-autosuggest-1-suggestion--0")))
        first_suggestion.click()
        print(f"Selected 'From' city: {from_city}")

        # --- TO CITY ---
        # Click on the "To" field (it might not need a click if it auto-focuses)
        to_field = wait.until(EC.element_to_be_clickable((By.ID, "toCity")))
        
        # Enter the city name in the input
        to_input = wait.until(EC.visibility_of_element_located((By.XPATH, "//input[@placeholder='To']")))
        to_input.send_keys(to_city)
        time.sleep(1) # Small delay for autosuggest
        
        # Click the first suggestion
        to_suggestion = wait.until(EC.element_to_be_clickable((By.ID, "react-autosuggest-1-suggestion--0")))
        to_suggestion.click()
        print(f"Selected 'To' city: {to_city}")

        # Note: Skipping date selection, will use default selected date.
        
        # --- CLICK SEARCH ---
        search_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//a[text()='Search']")))
        search_button.click()
        print("Clicked search button.")

    except Exception as e:
        print(f"Error during flight search: {e}")
        driver.quit()

def handle_results_popup():
    """Handles the popup on the search results page."""
    try:
        # Wait for the "OKAY, GOT IT!" button and click it
        okay_button = wait.until(EC.element_to_be_clickable((By.XPATH, "//button[text()='OKAY, GOT IT!']")))
        okay_button.click()
        print("Closed results page popup.")
    except TimeoutException:
        print("No results page popup found or it timed out.")
    except Exception as e:
        print(f"An error occurred while trying to close the results popup: {e}")

def scrape_flight_data():
    """Scrapes the flight data from the results page."""
    flights_data = []
    try:
        # Wait for the list of flights to be visible
        wait.until(EC.visibility_of_element_located((By.ID, "listing-id")))
        print("Flight listings are visible. Starting scrape...")
        
        # Give a few seconds for listings to render
        time.sleep(5) 

        # Find all flight cards
        flight_cards = driver.find_elements(By.XPATH, "//div[@class='listingCard']")
        print(f"Found {len(flight_cards)} flight cards.")
        
        if not flight_cards:
            print("No flight cards found. The page structure may have changed.")
            return []

        for card in flight_cards:
            try:
                # Extract data - Selectors are based on common MMT structure
                airline = card.find_element(By.XPATH, ".//p[contains(@class, 'airlineName')]").text
                dep_time = card.find_element(By.XPATH, ".//div[contains(@class, 'time-depart')]//p[1]").text
                arr_time = card.find_element(By.XPATH, ".//div[contains(@class, 'time-arrival')]//p[1]").text
                duration = card.find_element(By.XPATH, ".//p[contains(@class, 'duration')]").text
                price = card.find_element(By.XPATH, ".//div[contains(@class, 'price-section')]//p").text
                
                flight_info = {
                    "Airline": airline,
                    "DepartureTime": dep_time,
                    "ArrivalTime": arr_time,
                    "Duration": duration,
                    "Price": price
                }
                flights_data.append(flight_info)
                
            except NoSuchElementException:
                # This card might be an ad or a different layout
                print("Skipped a card (might be an ad or non-flight element).")
            except Exception as e:
                print(f"Error parsing a flight card: {e}")

    except Exception as e:
        print(f"Error during scraping: {e}")
        
    return flights_data

def save_to_excel(data, filename):
    """Saves the scraped data to an Excel file."""
    if not data:
        print("No data to save.")
        return
        
    try:
        df = pd.DataFrame(data)
        df.to_excel(filename, index=False)
        print(f"Successfully saved data to {filename}")
    except Exception as e:
        print(f"Error saving to Excel: {e}")

def main():
    try:
        driver.get("https://www.makemytrip.com/")
        handle_initial_popup()
        search_flights("Ahmedabad", "Hyderabad")
        handle_results_popup()
        flight_data = scrape_flight_data()
        save_to_excel(flight_data, "makemytrip_flights.xlsx")

    except Exception as e:
        print(f"An error occurred in the main process: {e}")
    finally:
        print("Automation finished. Closing browser.")
        time.sleep(3) # Keep browser open for 3 sec to see result
        driver.quit()

if __name__ == "__main__":
    main()