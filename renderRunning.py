import requests
import time

url = 'https://journey-genie-v2.onrender.com/'

def keep_website_active():
    try:
        while True:
            response = requests.get(url)
            if response.status_code == 200:
                print(f"Website is up and running! Status code: {response.status_code}")
            else:
                print(f"Failed to connect. Status code: {response.status_code}")
            
            # Wait for 5 minutes before sending another request
            time.sleep(40)  # 300 seconds = 5 minutes
    except KeyboardInterrupt:
        print("Process stopped by the user.")

if __name__ == "__main__":
    keep_website_active()
