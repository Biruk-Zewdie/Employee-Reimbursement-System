import axios from "axios";
import './Login.css'
import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { store } from "../../GlobalData/store";

export const Login: React.FC = () => {
    const [loginCreds, setLoginCreds] = useState({
        username: "",
        password: ""
    });

    const navigate = useNavigate();
    const usernameRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        if (usernameRef.current) {
            usernameRef.current.focus();
        }
    }, []);

    const storeValues = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        setLoginCreds((prevCreds) => ({ ...prevCreds, [name]: value }));
    };

    const login = async () => {
        try {
            const response = await axios.post("http://localhost:8080/auth/login", loginCreds, { withCredentials: true });

            console.log("Response:", response);
            store.loggedInUser = response.data;

            alert(store.loggedInUser.username + " has logged in! Welcome.");

            if (store.loggedInUser.role === "Manager") {
                navigate("manager/allEmployees");
            } else {
                navigate("/reimbursements");
            }
        } catch (error:any) {
            console.error("Login error:", error);
            
            if (error.response && error.response.data){

                // If it's an object, we need to handle it properly
            const errorMessage = error.response.data.message || JSON.stringify(error.response.data);
                alert(`Login unsuccessful: ${errorMessage}`)

            }else{
                alert("Login unsuccessful. Please check your credentials.");
            }
        }
    };

    const handleFormSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        await login();
    };

    return (
        <div className='login'>
            <h1>Welcome</h1>
            <h2>Log in</h2>
            <form onSubmit={handleFormSubmit}>
                <div className='login-form'>
                    <label htmlFor='username'>Username</label>
                    <input
                        type='text'
                        name='username'
                        id='username'
                        ref={usernameRef}
                        onChange={storeValues}
                    />

                    <label htmlFor='password'>Password</label>
                    <input
                        type='password'
                        name='password'
                        id='password'
                        onChange={storeValues}
                    />
                    <button className='login-data-submit-btn' type='submit'>Continue</button>
                </div>
            </form>
            <h5>New to Expense Reimbursement System?</h5>
            <button className='to-create-account-btn' onClick={() => navigate("/register")}>Register</button>
        </div>
    );
};