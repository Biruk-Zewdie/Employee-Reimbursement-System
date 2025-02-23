import axios from "axios"
import { useEffect, useState } from "react"
import {User} from "../../../../Interfaces/User.ts"

export const AllEmployeesTable: React.FC = () =>{

    const [users, setUsers] = useState<User[]> ([]);
    const [deleteEmployee, setDeleteEmployee] = useState<boolean> (false)

    useEffect (() =>{

        getAllEmployees();

    }, [])

    const getAllEmployees = async () =>{

        try {
            const response = await axios.get("http://localhost:8080/users", {withCredentials:true})
            console.log(response);

            setUsers(response.data);
        }catch (error:any){

            if (error.response) {
                // If backend provides an error message
                alert(`Error: ${error.response.data}`);
            } else {
                // If something else went wrong (e.g., network error)
                alert("Something went wrong trying to fetch users.");
            }
    
        }
    }

    const handleDeleteEmployee = async (user: User) => {
        try {
            const response = await axios.delete(`http://localhost:8080/users/${user.userId}`, {withCredentials:true})

            console.log(response)

            setUsers(users.filter((u) => u.userId != user.userId));

            alert("Employee deleted successfully!");

        }catch (error: any){
            
            if (error.response) {
                // If backend provides an error message
                alert(`Error: ${error.response.data}`);
            } else {
                // If something else went wrong (e.g., network error)
                alert("Something went wrong trying to fetch users.");
            }

        }
    }

    return (
        <div>
        <h2>Employees</h2>
            <table>
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Delete User</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        users.map((user:User) => (
                            <tr key={user.userId}>
                                <td>{user.userId}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.email}</td>
                                <td>{user.role}</td>
                                <td>
                                    <button onClick={() => handleDeleteEmployee(user)}>Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )

}