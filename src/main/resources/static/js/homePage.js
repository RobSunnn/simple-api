let requestCount = 0;
const requestLimit = 2; // Maximum requests allowed in the time frame
const timeFrame = 10 * 1000; // Time frame in milliseconds (1 minute)
let firstRequestTime = null;

document.getElementById('api-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const token = sessionStorage.getItem('apiToken');

    const currentTime = new Date().getTime();

    // Initialize first request time if this is the first request
    if (firstRequestTime === null) {
        firstRequestTime = currentTime;
    }

    // Check if the time frame has passed
    if (currentTime - firstRequestTime > timeFrame) {
        // Reset the counter and time
        requestCount = 1;
        firstRequestTime = currentTime;
    } else {
        // Increment request count
        requestCount++;
    }

    // Check if the limit is reached
    if (requestCount > requestLimit) {
        document.getElementById('response-output').innerHTML = JSON.stringify({ error: "Too many requests for demo. Add an API Key to avoid rate limiting." });
        return;
    }

    document.getElementById('response-container').style.display = 'block';
    document.getElementById('response-output').innerHTML = 'Loading...';

    // Make the API request with the token in headers
    fetch(`/quote`, {
        method: 'GET',
        headers: {
            'x-api-token': token  // Add the token to the header
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('API request failed');
            }
            return response.json();  // Expect a JSON response
        })
        .then(data => {
            const responseOutput = document.getElementById('response-output');

            if (data) {
                // Clear any previous content
                responseOutput.textContent = '';

                // Create a <pre> element for pretty-printing
                const preElement = document.createElement('pre');
                preElement.textContent = JSON.stringify(data, null, 2);  // Pretty-print JSON
                responseOutput.appendChild(preElement);  // Append it to the output div
            } else {
                // Handle the case where no data is returned
                responseOutput.textContent = 'No facts found.';
            }
        })
        .catch(error => {
            // Handle errors and display a message
            document.getElementById('response-output').innerHTML = `Error: ${error.message}`;
        });
});