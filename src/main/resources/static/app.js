const API_URL = "http://localhost:8080/api/students";
const API_AUTH =  "http://localhost:8080/api/auth"

// Create a new student
async function createStudent(studentData) {
    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(studentData),
            credentials: "include"
        })

        if(response.ok){
            alert("Student added successfully.")
            resetForm()
            getAllStudents()
        }else{
            alert("Error adding student.")
        }
    } catch (error) {
        console.error("Error Creating a student: ", error)
    }
}

// Handle student form submission
document.getElementById("studentForm").addEventListener("submit", function(e){
    e.preventDefault()

    if(!validateStudentForm()){
        return
    }

    const id = document.getElementById("studentId").value

    const studentData = {
        firstName: document.getElementById("firstName").value.trim(),
        lastName: document.getElementById("lastName").value.trim(),
        email: document.getElementById("email").value.trim()
    }

    if(id) {
        updateStudent(id, studentData)
    } else {
        createStudent(studentData)
    }
})

// Reset the student form
function resetForm() {
    document.getElementById("studentId").value = ""
    document.getElementById("studentForm").reset()

    const submitBtn = document.getElementById("submitBtn")
    submitBtn.textContent = "Add Student"
    submitBtn.style.backgroundColor = "#28a745"
}

// Get all students and display them in the table
async function getAllStudents() {
    try {
        const response = await fetch(API_URL, {
            credentials: "include"
        })

        if(!response.ok) throw new Error("Network response was not ok")
       
        const students = await response.json()
        const tableBody = document.getElementById("studentTableBody")
        tableBody.innerHTML= ""

        students.forEach(student => {
            const row  = `
                <tr>
                    <td>${student.id}</td>
                    <td>${student.firstName}</td>
                    <td>${student.lastName}</td>
                    <td>${student.email}</td>
                    <td>
                        <button 
                            class="btn-edit" 
                            onClick="initializeStudentFormById(${student.id})">
                                Edit
                        </button>
                        <button 
                            class="btn-delete" 
                            onClick="deleteStudent(${student.id})">
                                Delete
                        </button>
                    </td>
                </tr>
            `
            tableBody.innerHTML += row
        });
    } catch(error) {
        console.log("Error fetching students: ", error)
        alert("Failed to fetch Students. Is your Spring Boot backend running?")
    }
}


document.addEventListener("DOMContentLoaded", getAllStudents)

// Load student details into the form for editing
async function initializeStudentFormById(id) {
    try {
        const response = await fetch (`${API_URL}/${id}`, {
            credentials: "include"
        })
        if(!response.ok) throw new Error ("Could not fetch student details")
       
        const student = await response.json()

        document.getElementById("studentId").value = student.id
        document.getElementById("firstName").value = student.firstName
        document.getElementById("lastName").value = student.lastName
        document.getElementById("email").value = student.email

        const SubmitBtn = document.getElementById("submitBtn")
        submitBtn.textContent = "Update"
        submitBtn.style.backgroundColor = "#007bff"

        clearValidationStyles()
    } catch (error) {
        console.log("Error fecthing student details: ", error)
    }
}

// Update an existing student
async function updateStudent(id, studentData) {
    try {
        const response = await fetch (`${API_URL}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify(studentData),
            credentials: "include"
        })

        if(response.ok) {
            alert("Student addedd successfully!")
            resetForm()
            getAllStudents()
        }else {
            alert("Error updating student.")
        }
    } catch (error) {
        console.log("Error updating student: ", error)
    }
}

// Delete a student after confirmation
async function deleteStudent(id) {
    if(confirm("Are you sure you want to delete thi student?")) {
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: "DELETE",
                credentials: "include"
            })

            if(response.ok) {
                const message = await response.text();
                alert(message)
                getAllStudents()
            } else {
                alert("Error deleting student.")
            }
        } catch (error) {
            console.log("Error deleting student: ", error)
        }
    }
}

// Log in a user using their credentials
async function loginUser(loginDto) {
    try {
        const response = await fetch(`${API_AUTH}/auth_login_session`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(loginDto),
            credentials: "include"
        });

        if (response.ok) {
            alert("Login successfully!");
            getAllStudents();
        } else {
            alert("Invalid username or password.");
        }
    } catch (error) {
        console.log("Login error: ", error);
    }
}

// Log out the current user
async function logoutUser() {
    try {
        await fetch (`${API_AUTH}/logout`, {
            method: "POST",
            credentials: "include"
        })

        document.getElementById("studentTableBody").innerHTML =""
        resetForm()
        alert("Logged out successfully")

        try{
            await fetch (API_URL, {
                method: "GET",
                headers: {
                    "Authorization": "Basic" + 
                            btoa("invalid_user:wrong_password")
                }
            })
        } catch (err) {
            console.log("Browser credential cleared.")
        }
        window.location.reload()
    } catch (e) {
        console.error("Logout transmission crash: ", e)
    }
}