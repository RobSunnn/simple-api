function generateUUID() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        const r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}

document.addEventListener("DOMContentLoaded", function () {
    const token = generateUUID();
    sessionStorage.setItem('apiToken', token);

    // Store the token on the server
    fetch('http://localhost:8080/store-token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ token: token })
    }).then(response => console.log('Token stored:', response));
});

// Remove token when tab is closed
window.addEventListener("beforeunload", function () {
    const token = sessionStorage.getItem('apiToken');

    if (token) {
        fetch('http://localhost:8080/remove-token', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ token: token })
        }).then(response => console.log('Token removed:', response));
    }
});