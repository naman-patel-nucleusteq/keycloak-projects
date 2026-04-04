import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createEmployee } from "../services/EmployeeService";

const AddEmployee = () => {

    
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    

    const navigate = useNavigate();

    // handle submit
    const handleSubmit = (e) => {
        e.preventDefault();

        const employee = {firstName, lastName, email}    //creating employee object
        // console.log(employee);
        
        createEmployee(employee).then((res)=>{         //API function from EmployeeService.js
            console.log(res.data);
            navigate("/employees");
            alert("Employee Added Successfully!");
        });
        
        // reset form
        setFirstName("");
        setLastName("");
        setEmail("");
        
    };



    return (
        <div className="flex justify-center items-center mt-10">
            <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">

                <h2 className="text-2xl font-bold text-center mb-6 text-gray-800">
                    Add Employee
                </h2>

                <form onSubmit={handleSubmit} className="space-y-5">

                    {/* First Name */}
                    <div>
                        <label className="block text-sm font-medium text-gray-600 mb-1">
                            First Name
                        </label>
                        <input
                            type="text"
                            name="firstName"
                            placeholder="Enter first name"
                            required
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>

                    {/* Last Name */}
                    <div>
                        <label className="block text-sm font-medium text-gray-600 mb-1">
                            Last Name
                        </label>
                        <input
                            type="text"
                            name="lastName"
                            placeholder="Enter last name"
                            required
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>

                    {/* Email */}
                    <div>
                        <label className="block text-sm font-medium text-gray-600 mb-1">
                            Email
                        </label>
                        <input
                            type="email"
                            name="email"
                            placeholder="Enter email"
                            required
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="w-full px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>

                    {/* Submit Button */}
                    <button
                        type="submit"
                        className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition font-semibold"
                    >
                        Add Employee
                    </button>

                </form>
            </div>
        </div>
    );
};

export default AddEmployee;

