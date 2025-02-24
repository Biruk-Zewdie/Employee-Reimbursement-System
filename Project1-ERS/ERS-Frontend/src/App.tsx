
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import { Login } from './Components/Authentication/Login'
import { Register } from './Components/Authentication/Register'
import { AllEmployeesTable } from './Components/DashBoards/ManagersDashboard/Users/AllEmployeesTable'
import { AllReimbursementsTable } from './Components/DashBoards/ManagersDashboard/Reimbursements/AllReimbursementsTable'
import { CreateReimbursement } from './Components/DashBoards/CreateReimbursement'
import { MyReimbursements } from './Components/DashBoards/EmployeeDashboard/myReimbursements'


function App() {
  

  return (
    <>
      <BrowserRouter>
      <Routes>
        <Route path="" element={<Login/>}/>
        <Route path='/register' element={<Register/>}/>
        <Route path='/manager/allEmployees' element = {<AllEmployeesTable/>}/>
        <Route path='/manager/allReimbursements' element = {<AllReimbursementsTable/>}/>
        <Route path='/createReimbursement' element = {<CreateReimbursement/>}/>
        <Route path='/myReimbursements' element={<MyReimbursements/>}/>
      </Routes>
      </BrowserRouter>
    {/* <Login /> */}
    
       
    </>
  )
}

export default App
