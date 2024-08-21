import React, { useState } from "react";
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate } from "react-router-dom";
import logo from '../logo.svg'; // Brand image

const EmployeeSearch = () => {
    const [employeeId, setEmployeeId] = useState("");
    const [employees, setEmployees] = useState([]);
    const navigate = useNavigate(); // Navigation hook

    const handleSearch = () => {
        let url = "http://localhost:8080/employees";

        if (employeeId) {
            url = `http://localhost:8080/employee?id=${employeeId}`;
        }

        axios.get(url)
            .then(response => {
                const data = Array.isArray(response.data) ? response.data : [response.data];
                console.log(response);
                setEmployees(data);
            })
            .catch(error => {
                console.error("There was an error fetching the employee data!", error);
                setEmployees([]);
            });
    };

    const handleAllEmployees = () => {
        navigate('/employees'); // Show all employees
        handleSearch(); // Find all employees
    };

    return (
        <div>
            {/* Navbar menu*/} 
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="#">
                    <img src={logo} alt="Brand Logo" width="100" height="40" className="d-inline-block align-top" />
                </a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item active">
                            <a className="nav-link" href="/" onClick={(e) => {
                                e.preventDefault();
                                navigate('/');
                            }}>Home</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#" onClick={(e) => {
                                e.preventDefault();
                                handleAllEmployees();
                            }}>All Employees</a>
                        </li>
                    </ul>
                </div>
            </nav>

            <div className="container mt-4">
                <h1 className="text-center mb-4">Employee Search</h1>
                <div className="row mb-4">
                    <div className="col-md-6 offset-md-3">
                        <div className="input-group">
                            <input
                                type="text"
                                className="form-control"
                                placeholder="Enter Employee ID"
                                value={employeeId}
                                onChange={(e) => setEmployeeId(e.target.value)}
                            />
                            <button className="btn btn-primary" onClick={handleSearch}>Search</button>
                        </div>
                    </div>
                </div>

                {employees.length > 0 && (
                    <div className="table-responsive">
                        <table className="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Salary</th>
                                    <th>Age</th>
                                    <th>Annual Salary</th>
                                </tr>
                            </thead>
                            <tbody>
                                {employees.map((employee) => (
                                    <tr key={employee.id}>
                                        <td>{employee.id}</td>
                                        <td>{employee.employee_name}</td>
                                        <td>{employee.employee_salary}</td>
                                        <td>{employee.employee_age}</td>
                                        <td>{employee.annualSalary}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
};

export default EmployeeSearch;
