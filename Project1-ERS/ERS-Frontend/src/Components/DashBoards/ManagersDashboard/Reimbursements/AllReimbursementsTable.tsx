import { useEffect, useState } from "react";
import { Reimbursement } from "../../../../Interfaces/Riembursement"
import axios from "axios";
import './AllReimbursementsTable.css'

export const AllReimbursementsTable: React.FC = () => {

    const [reimbursements, setReimbursements] = useState <Reimbursement []> ([]);

    const [status, setStatus] = useState <string> ("all")
    


    useEffect (() => {

        getAllReimbursements();

    }, [status])      //// Re-fetch data whenever the filter changes


    const getAllReimbursements = async () => {

        try {

            let url = "http://localhost:8080/reimbursements";
            if (status !== "all"){
                url = `http://localhost:8080/reimbursements?status=${status}`;
            }

            const response = await axios.get(url, {withCredentials:true})

            console.log (response);
            console.log (status);

            setReimbursements(response.data);

        }catch (error: any){
            if (error.response && error.response.data){

                const errorMessage = error.response.data.message || JSON.stringify(error.response.data);

                alert(`Error: ${errorMessage}`);

            }else {
                alert("Something went wrong trying to fetch users.");
            }
        }
    }

    const handleDeleteReimbursement = async (reimbursement:Reimbursement) => {

        try {

            const response = await axios.delete(`http://localhost:8080/reimbursements/${reimbursement.requestId}`, {withCredentials: true});

            console.log(response)

            setReimbursements(reimbursements.filter((r) => r.requestId != reimbursement.requestId));

            alert("Reimbursement deleted successfully!");
        }catch (error : any){

            if (error.response){
                alert (`Error: ${error.response.data}`);
            }else {
                alert ("Something went wrong trying to fetch users.");
            }
        }
    }

    const handleUpdateReimbursement = async (reimbursement: Reimbursement, newStatus: string) => {

        try {
            const response = await axios.patch(
                `http://localhost:8080/reimbursements/${reimbursement.requestId}/status?status=${newStatus}`,
                {},
                 {withCredentials: true});

            console.log (response)

            setReimbursements((prev) => prev.map((r) => r.requestId === reimbursement.requestId ? {...r, reimbursementStatus: newStatus} : r));
        }catch (error: any){
            alert ("Error in updating reimbursement status")
        }

    }

    return (
        <div>
            <h2> Reimbursements</h2>
              {/* Radio buttons for filtering */}
              <div>
                <label>
                    <input
                        type="radio"
                        name="status"
                        value="all"
                        checked={status === "all"}
                        onChange={() => setStatus("all")}
                    />
                    All
                </label>
                <label>
                    <input
                        type="radio"
                        name="status"
                        value="pending"
                        checked={status === "pending"}
                        onChange={() => setStatus("pending")}
                    />
                    Pending
                </label>
                <label>
                    <input
                        type="radio"
                        name="status"
                        value="approved"
                        checked={status === "approved"}
                        onChange={() => setStatus("approved")}
                    />
                    Approved
                </label>
                <label>
                    <input
                        type="radio"
                        name="status"
                        value="denied"
                        checked={status === "denied"}
                        onChange={() => setStatus("denied")}
                    />
                    Denied
                </label>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Request ID</th>
                        <th>Requested By</th>
                        <th>Email</th>
                        <th>Amount</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Decision (Approve/Deny/Delete)</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        reimbursements.map((reimbursement: Reimbursement) => (
                            <tr key = {reimbursement.requestId}>
                                <td>{reimbursement.requestId}</td>
                                <td>{reimbursement.user.firstName}</td>
                                <td>{reimbursement.user.email}</td>
                                <td>{reimbursement.amount}</td>
                                <td>{reimbursement.description}</td>
                                <td>{reimbursement.reimbursementStatus}</td>
                                <td>
                                    <button onClick={() => handleUpdateReimbursement(reimbursement, "approved")}
                                        disabled={reimbursement.reimbursementStatus !== "pending"}>
                                            Approve
                                    </button>
                                    <button onClick={() => handleUpdateReimbursement(reimbursement, "denied")}
                                        disabled={reimbursement.reimbursementStatus != "pending"}>
                                            Deny
                                    </button>
                                    <button onClick={() => handleDeleteReimbursement(reimbursement)}>Delete</button>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )

}