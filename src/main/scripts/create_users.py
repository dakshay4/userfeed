import requests

# List of names with IDs
users = [
    {"name": "dakshay"},
    {"name": "Elon Musk"},
    {"name": "Jeff Bezos"},
    {"name": "Mark Zuckerberg"},
    {"name": "Bill Gates"},
    {"name": "Larry Page"},
    {"name": "Steve Jobs"},
    {"name": "Tim Cook"},
    {"name": "Nitin Kamath"},
    {"name": "Sachin Bansal"},
    {"name": "Kunal Shah"}
]

# Define the URL
url = 'http://localhost:8080/users/'

# Define the headers
headers = {
    'Content-Type': 'application/json'
}

# Iterate through each user and send a POST request
for user in users:
    response = requests.post(url, headers=headers, json=user)

    # Print the response for each request
    print(f"POST request sent for user: {user['name']}")
    print(f"Status Code: {response.status_code}")
    print(f"Response Body: {response.text}\n")
