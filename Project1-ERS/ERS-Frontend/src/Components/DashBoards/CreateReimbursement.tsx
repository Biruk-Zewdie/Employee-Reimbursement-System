import React, { useState } from 'react';
import axios from 'axios';
import './CreateReimbursement.css';
import { useNavigate } from 'react-router-dom';

interface Reimbursement {
    amount: string;
    description: string;
    reimbursementStatus: string;
}

export const CreateReimbursement: React.FC = () => {
    const [reimbursement, setReimbursement] = useState<Reimbursement>({
        amount: '',
        description: '',
        reimbursementStatus: 'pending',
    });

    const [error, setError] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(false);

    const navigate = useNavigate();

    // Retrieve userId from localStorage
    const userId = localStorage.getItem("userId");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
        setReimbursement({ ...reimbursement, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        if (!userId || !reimbursement.amount || !reimbursement.description) {
            setError('Please fill in all fields.');
            return;
        }

        setLoading(true);
        setError('');

        try {
            const requestBody = {
                user: { userId: Number(userId) }, // Convert userId to number
                amount: reimbursement.amount,
                description: reimbursement.description,
                reimbursementStatus: reimbursement.reimbursementStatus,
            };

            const response = await axios.post('http://localhost:8080/reimbursements', requestBody, { withCredentials: true });

            console.log(response);
            alert('Reimbursement submitted successfully!');
            
            setReimbursement({
                amount: '',
                description: '',
                reimbursementStatus: 'pending',
            });

            navigate('/myReimbursements');  

        } catch (error) {
            console.error("Error:", error);
            setError('Failed to submit reimbursement. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="create-reimbursement">
            <h2>Create Reimbursement</h2>
            <button onClick={() => navigate("/myReimbursements")}>Back</button>
            <form className="create-reimbursement-form" onSubmit={handleSubmit}>
                <label>Amount ($)</label>
                <input
                    type="number"
                    name="amount"
                    value={reimbursement.amount}
                    onChange={handleChange}
                    placeholder="Enter amount"
                    required
                />

                <label>Description</label>
                <textarea
                    name="description"
                    value={reimbursement.description}
                    onChange={handleChange}
                    placeholder="Enter description"
                    required
                />

                <label>Status</label>
                <select name="reimbursementStatus" value={reimbursement.reimbursementStatus} onChange={handleChange}>
                    <option value="pending">Pending</option>
                    <option value="approved">Approved</option>
                    <option value="denied">Denied</option>
                </select>

                {error && <p className="error-message">{error}</p>}

                <button type="submit" className="create-reimbursement-form-submit-btn" disabled={loading}>
                    {loading ? 'Submitting...' : 'Submit Request'}
                </button>
            </form>
        </div>
    );
};
