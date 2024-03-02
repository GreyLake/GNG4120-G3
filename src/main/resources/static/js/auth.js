// Function to validate password and password verify fields
function validatePasswords() {
    var password = document.getElementById('password').value;
    var passwordVerify = document.getElementById('passwordverify').value;

    if (password !== passwordVerify) {
        alert('Passwords do not match');
        return false; // Prevent form submission
    }

    return true; // Allow form submission
}

// Add event listener to form submit event
document.getElementById('signupForm').addEventListener('submit', function(event) {
    if (!validatePasswords()) {
        event.preventDefault(); // Prevent form submission if passwords don't match
    }
});