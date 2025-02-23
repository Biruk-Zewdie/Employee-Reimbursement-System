import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import './Register.css'

interface FormDataType {
    firstName: string;
    lastName: string;
    username: string;
    email: string;
    password: string;
    confirmPassword: string;
    role: string;
}

export const Register: React.FC = () => {
    const initial_state: FormDataType = {
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
        role: "Employee", // Default role to prevent uncontrolled component issues
    };

    const [user, setUser] = useState<FormDataType>(initial_state);
    const navigate = useNavigate();

    const handleChange = (event: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
        const { name, value } = event.target;
        setUser(prevState => ({ ...prevState, [name]: value }));
    };

    const handleRegister = async () => {
        if (user.password !== user.confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        try {
            const { confirmPassword, ...userData } = user;
            const response = await axios.post("http://localhost:8080/auth/register", userData);

            console.log(response)
            alert("Registration successful! You can now log in.");
            navigate("/");
        } catch (error: any) {
            console.error("Registration failed", error);
            
            // Check if error has a response and extract the message
            if (error.response && error.response.data) {
                alert(`Registration failed: ${error.response.data}`);
            } else {
                alert("Registration failed. Please try again.");
            }

        }
    };

    const handleLoginButtonClick = () => {
        navigate('/');
    };

    return (
        <div className='create-account'>
            <h2>Create Account</h2>

            <form onSubmit={(e) => { e.preventDefault(); handleRegister(); }}>
                <div className='create-account-form'>
                    <label htmlFor='firstName'>First Name</label>
                    <input
                        type='text'
                        name='firstName'
                        id='firstName'
                        value={user.firstName}
                        onChange={handleChange}
                        required
                    />

                    <label htmlFor='lastName'>Last Name</label>
                    <input
                        type='text'
                        name='lastName'
                        id='lastName'
                        value={user.lastName}
                        onChange={handleChange}
                        required
                    />

                    <label htmlFor='username'>Username</label>
                    <input
                        type='text'
                        name='username'
                        id='username'
                        value={user.username}
                        onChange={handleChange}
                        required
                    />

                    <label htmlFor='email'>Email</label>
                    <input
                        type='email'
                        name='email'
                        id='email'
                        value={user.email}
                        onChange={handleChange}
                        required
                    />

                    <label htmlFor='password'>Password</label>
                    <input
                        type='password'
                        name='password'
                        id='password'
                        value={user.password}
                        onChange={handleChange}
                        required
                    />

                    <label htmlFor='confirmPassword'>Confirm Password</label>
                    <input
                        type='password'
                        name='confirmPassword'
                        id='confirmPassword'
                        value={user.confirmPassword}
                        onChange={handleChange}
                        required
                    />

                    <label htmlFor='role'>Role</label>
                    <select name="role" id="role" value={user.role} onChange={handleChange} required>
                        <option value="Employee">Employee</option>
                        <option value="Manager">Manager</option>
                    </select>

                    <button className='create-account-form-submit-btn' type='submit'>
                        Register
                    </button>
                </div>
            </form>

            <h5>Already have an account?</h5>
            <button className='to-login-page-btn' onClick={handleLoginButtonClick}>
                Log in
            </button>
        </div>
    );
};