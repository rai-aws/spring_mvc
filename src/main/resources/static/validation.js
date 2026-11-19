
const form = document.getElementById("studentForm");

form.addEventListener("submit", function(event) {
    clearErrors();

    let isValid = true;

    const firstName = document.getElementById("firstName").value.trim();
    const lastName = document.getElementById("lastName").value.trim();
    const email = document.getElementById("email").value.trim();

    // First Name validation
    if (firstName.length < 2 || firstName.length > 50) {
        showError("firstName", "First name must be 2-50 characters.");
        isValid = false;
    }

    // Last Name validation
    if (lastName.length < 2 || lastName.length > 50) {
        showError("lastName", "Last name must be 2-50 characters.");
        isValid = false;
    }

    // Email validation
    const emailPattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/i;

    if (!emailPattern.test(email)) {
        showError("email", "Please enter a valid email address.");
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    }
});

// Display error message
function showError(field, message) {
    document.getElementById(field + "Error").textContent = message;
}

// Clear error messages
function clearErrors() {
    document.getElementById("firstNameError").textContent = "";
    document.getElementById("lastNameError").textContent = "";
    document.getElementById("emailError").textContent = "";
}
