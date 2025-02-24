import axios from "axios";
import { useEffect, useState } from "react";
import { Reimbursement } from "../../../Interfaces/Riembursement"
import { LogoutButton } from "../../Authentication/LogoutButton";
import "./MyReimbursements.css";
import { useNavigate } from "react-router-dom";

export const MyReimbursements: React.FC = () => {
  const [myReimbursements, setMyReimbursements] = useState<Reimbursement[]>([]);
  const [firstName, setFirstName] = useState<string>('');
  const [status, setStatus] = useState<string>("all"); // Added state for filtering by status

  const navigate = useNavigate();

  useEffect(() => {
    getMyReimbursements();
  }, [status]);  // Trigger on status change

  const handleRequestReimbursement = () => {
    navigate("/createReimbursement");
  }

  // Function to handle status change from radio buttons
  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setStatus(event.target.value);
  }

  const getMyReimbursements = async () => {
    const userId = localStorage.getItem("userId");
  
    if (!userId) {
      alert("User not logged in!");
      return;
    }
  
    try {
      const response = await axios.get(`http://localhost:8080/reimbursements/user/${userId}?status=${status}`, { withCredentials: true });
  
      console.log("Status:", status);
      console.log("API Response:", response.data);
  
      setMyReimbursements(response.data);
  
      if (response.data.length > 0) {
        setFirstName(response.data[0].user.firstName);
      } else {
        setFirstName('');
      }
  
    } catch (error: any) {
      if (error.response && error.response.data) {
        const errorMessage = error.response.data.message || JSON.stringify(error.response.data);
        alert(`Error: ${errorMessage}`);
      } else {
        alert("Something went wrong trying to fetch reimbursements.");
      }
    }
  };
  

  return (
    <div className="container">
      <h1 className="header">{firstName}'s Reimbursements</h1>
      <button className="request-btn" onClick={handleRequestReimbursement}>
        Request Reimbursement
      </button>
      
      {/* Radio Buttons for Filtering */}
      <div className="filter-container">
        <label>
          <input
            type="radio"
            name="status"
            value="all"
            checked={status === "all"}
            onChange={handleStatusChange}
          />
          All
        </label>
        <label>
          <input
            type="radio"
            name="status"
            value="pending"
            checked={status === "pending"}
            onChange={handleStatusChange}
          />
          Pending
        </label>
        <label>
          <input
            type="radio"
            name="status"
            value="approved"
            checked={status === "approved"}
            onChange={handleStatusChange}
          />
          Approved
        </label>
        <label>
          <input
            type="radio"
            name="status"
            value="denied"
            checked={status === "denied"}
            onChange={handleStatusChange}
          />
          Denied
        </label>
      </div>

      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>Request ID</th>
              <th>Requested By</th>
              <th>Email</th>
              <th>Amount</th>
              <th>Description</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            {myReimbursements.map((reimbursement: Reimbursement) => (
              <tr key={reimbursement.requestId}>
                <td>{reimbursement.requestId}</td>
                <td>{reimbursement.user.firstName}</td>
                <td>{reimbursement.user.email}</td>
                <td>{reimbursement.amount}</td>
                <td>{reimbursement.description}</td>
                <td>{reimbursement.reimbursementStatus}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      
      <LogoutButton />
    </div>
  );
};
