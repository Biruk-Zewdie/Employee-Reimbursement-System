import axios from "axios"
import { useEffect, useState } from "react"
import { Reimbursement } from "../../../Interfaces/Riembursement"  // Make sure the path is correct
import { useNavigate } from "react-router-dom"
import { LogoutButton } from "../../Authentication/LogoutButton"

export const MyReimbursements: React.FC = () => {
  const [myReimbursements, setMyReimbursements] = useState<Reimbursement[]>([])
  const [firstName, setFirstName] = useState<string>(''); 
  const [status, setStatus] = useState<string>("all")

  useEffect(() => {
    getMyReimbursements()
  }, [])

  const navigate = useNavigate();

  const handleRequestReimbursement = () => {
    navigate("/createReimbursement")
  }

  const getMyReimbursements = async () => {
    // Retrieve the userId from localStorage
    const userId = localStorage.getItem("userId")

    if (!userId) {
      alert("User not logged in!")
      return
    }

    try {
      const response = await axios.get(`http://localhost:8080/reimbursements/user/${userId}`, { withCredentials: true })

      console.log(response)
      setMyReimbursements(response.data)
      setFirstName(response.data[0].user.firstName)

    } catch (error: any) {
      if (error.response && error.response.data) {
        const errorMessage = error.response.data.message || JSON.stringify(error.response.data)
        alert(`Error: ${errorMessage}`)
      } else {
        alert("Something went wrong trying to fetch reimbursements.")
      }
    }
  }

  return (
    <div>
        <h1 style={{ fontSize: '38px' }}>{firstName}'s Reimbursements</h1>
        <button onClick={handleRequestReimbursement}>request reimbursement</button>
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
        <LogoutButton/>
    </div>
  )
}
