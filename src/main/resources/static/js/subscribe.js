const subscribeForm = document.getElementById('subscribe-form');
subscribeForm.addEventListener('submit', async function (e) {
    e.preventDefault();

    const token = sessionStorage.getItem('apiToken');
    const subscriberEmail = document.getElementById('subscriber-email');

    const formData = new FormData();
    formData.append('subscriberEmail', subscriberEmail.value);

    const response = await fetch('/add-subscriber', {
        method: 'POST',
        headers: {
            'x-api-token': token
        },
        body: formData
    });

    const responseData = await response.json();

    if (responseData.success) {
        showPopup("Thank you for your subscription!");
        clearErrors();
        subscriberEmail.value = '';
    } else {
        displayErrors(responseData.errors);
    }

});

function showPopup(message) {
    // Create a div for the popup
    const popup = document.createElement('div');
    popup.classList.add('popup');
    popup.innerText = message;

    document.body.appendChild(popup);

    setTimeout(() => {
        document.body.removeChild(popup);
    }, 5000);
}

function displayErrors(errors) {
    // Concatenate all error messages
    const errorMessage = errors.map(error => error.defaultMessage).join(' ');

    const errorDiv = document.getElementById('error-message');
    errorDiv.innerText = errorMessage; // Set the error message text
    errorDiv.style.display = 'block'; // Make the error message div visible
}

function clearErrors() {
    const errorDiv = document.getElementById('error-message');
    errorDiv.innerText = ''; // Clear the error message text
    errorDiv.style.display = 'none'; // Hide the error message div
}