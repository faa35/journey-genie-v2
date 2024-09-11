import requests
import time

url = 'https://journey-genie-v2.onrender.com/'

def keep_website_active():
    try:
        count = 1  # Start counting from 1
        while True:
            response = requests.get(url)
            if response.status_code == 200:
                print(f"{count}. Website is up and running! Status code: {response.status_code}")
            else:
                print(f"{count}. Failed to connect. Status code: {response.status_code}")
            
            # Increment the count and wait for 40 seconds before sending another request
            count += 1
            time.sleep(40)  # 40 seconds
    except KeyboardInterrupt:
        print("Process stopped by the user.")

if __name__ == "__main__":
    keep_website_active()
