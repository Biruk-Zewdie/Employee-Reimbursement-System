import { User } from "./User";

export interface Reimbursement {

    requestId: number,
    requestDate: Date,
    amount: number,
    description: string,
    reimbursementStatus: string,
    user: User


}
