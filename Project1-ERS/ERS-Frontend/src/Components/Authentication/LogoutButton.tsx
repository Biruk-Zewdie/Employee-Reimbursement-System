import axios from "axios";
import { useNavigate } from "react-router-dom"

export const LogoutButton: React.FC = () => {

    const navigate = useNavigate();

    const handleLogout = async () =>{

        try {
            await axios.post("http://localhost:8080/auth/logout", {}, { withCredentials: true } );

            localStorage.removeItem("userId");

            navigate("/")
        }catch (error) {
            console.error("Logout failed:", error);
            alert("Failed to log out. Please try again.");
        }


    }

    return (

        <button onClick={handleLogout}>Logout</button>
    )


    
}