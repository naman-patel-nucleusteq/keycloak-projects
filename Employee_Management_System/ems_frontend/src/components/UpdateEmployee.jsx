import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getEmployeeById, updateEmployee } from "../services/EmployeeService";


const UpdateEmployee = () => {

    const { id } = useParams();     // get id from URL
    const navigate = useNavigate();

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");



    //  Fetch employee data by id
    useEffect(() => {
        getEmployeeById(id)               //API function from EmployeeService.js
            .then((res) => {
                setFirstName(res.data.firstName);
                setLastName(res.data.lastName);
                setEmail(res.data.email);
            })
            .catch((err) => {
                console.error(err);
            });
    }, [id]);



    // Handle update
    const handleSubmit = (e) => {
        e.preventDefault();

        const employee = { firstName, lastName, email }     //creating employee object

        updateEmployee(id, employee)
            .then(() => {
                alert("Employee Updated Successfully!");
                navigate("/employees");
            })
            .catch((err) => {
                console.error(err);
            });
    };



    return (
        <div className="flex justify-center items-center mt-10">
            <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md">

                <h2 className="text-2xl font-bold text-center mb-6 text-gray-800">
                    Update Employee
                </h2>


                <form onSubmit={handleSubmit} className="space-y-5">

                    {/* First Name */}
                    <label className="block text-sm font-medium text-gray-600 mb-1">
                        First Name
                    </label>
                    <input
                        type="text"
                        name="firstName"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        placeholder="First Name"
                        className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                    />


                    {/* Last Name */}
                    <label className="block text-sm font-medium text-gray-600 mb-1">
                        Last Name
                    </label>
                    <input
                        type="text"
                        name="lastName"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        placeholder="Last Name"
                        className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                    />

                    {/* Email */}
                    <label className="block text-sm font-medium text-gray-600 mb-1">
                        Email
                    </label>
                    <input
                        type="email"
                        name="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Email"
                        className="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500"
                    />

                    {/* Button */}
                    <button
                        type="submit"
                        className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition cursor-pointer font-semibold"
                    >
                        Update Employee
                    </button>

                </form>
            </div>
        </div>
    );
};

export default UpdateEmployee;