document.getElementById('create-post-form').addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent the default form submission
    const token = sessionStorage.getItem('apiToken');

    // Access the form fields
    const title = document.querySelector('input[placeholder="Post Title"]').value;
    const content = document.querySelector('textarea[placeholder="Post Content"]').value;
    const file = document.querySelector('input[type="file"]').files[0];

    // Validate the file
    if (!file || file.size > 5 * 1024 * 1024) { // 5MB limit
        alert('Please upload a valid image (max size 5MB).');
        return;
    }

    // Create FormData and append form values
    const formData = new FormData();
    formData.append('title', title);
    formData.append('content', content);
    formData.append('file', file);

    try {
        // Send form data using fetch
        const response = await fetch('/blog/create-post', {
            method: 'POST',
            headers: {
                'x-api-token': token
            },
            body: formData
        });

        if (response.ok) {
            // Show the success popup
            const successPopup = document.getElementById('success-popup');
            successPopup.style.display = 'block';

            setTimeout(() => {
                successPopup.style.display = 'none';
            }, 5000);

            // Optionally reset the form after successful submission
            document.getElementById('create-post-form').reset();
        } else {
            const errorMessage = await response.text();
            alert('Failed to create the post: ' + errorMessage);
        }
    } catch (error) {
        console.error('Error submitting the form:', error);
        alert('An error occurred while submitting the form.');
    }
});