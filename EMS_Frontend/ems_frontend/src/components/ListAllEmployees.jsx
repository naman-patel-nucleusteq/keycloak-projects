import React, { useEffect, useState } from 'react'
import { listAllEmployees, deleteEmployee as DeleteEmployeeAPI } from '../services/EmployeeService';
import { useNavigate } from 'react-router-dom';

const ListAllEmployees = () => {
    

    const dumyData = [
        {
            id: 1,
            firstName: "Naman",
            lastName: "Patel",
            email: "naman@gmail.com"
        },
        {
            id: 2,
            firstName: "Damita",
            lastName: "Pathak",
            email: "damita@gmail.com"
        },
        {
            id: 3,
            firstName: "Naman2",
            lastName: "Patel",
            email: "naman2@gmail.com"
        },
        {
            id: 4,
            firstName: "Naman3",
            lastName: "Patel3",
            email: "naman3@gmail.com"
        },
    ]


    const navigate = useNavigate();
    const [employees, setEmployees] = useState([]);       //stores list of employees (initially its stores an empty array)    //employees -> state variable , setEmployees -> function that updates the state variable
    const [searchText, setSearchText] = useState("");     //it stores the text which is searched (or typed in search bar)


    useEffect(() => {
        getAllEmployees();
    }, [])
    

    const getAllEmployees = () => {
        listAllEmployees().then((res) => {
            setEmployees(res.data);           //from backend we send 2 fields -> ResponseEnitity<>(data, status)...so here we are accesing data from response using "res.data"
        }).catch((err) => {
            console.error(err);
        })
    }


    // Filter employees based on search
    // const filteredEmployees = dumyData.filter((emp) => {              //It loops through each employee and returns only those that match the condition
    const filteredEmployees = employees.filter((emp) => {
        let employeeName = `${emp.firstName} ${emp.lastName}`;        //string that Combines first name + last name (also add space b/w them)

        return employeeName.toLowerCase()                            //Converts text to lowercase
            .startsWith(searchText.toLowerCase())                    //Checks if string(ie employeeName) starts with the search text or not, For Eg : "naman patel".startsWith("nam") → true  , "naman patel".startsWith("xyz") → false
    });

    

    //Update employee
    function updateEmployee(id){
        navigate(`/edit-employee/${id}`);         //naviage to UpdateEmployee.jsx 
    }


    //delete employee
    function deleteEmployee(id){
        const confirmDelete = window.confirm("Are you sure you want to delete?");
        if(!confirmDelete) return;

        DeleteEmployeeAPI(id)         // //API function from EmployeeService.js
        .then((res)=>{
         console.log(res.data);   
          getAllEmployees();         //Updates UI after deleting employee
        })
        .catch((err) => {
            console.error(err);
        });
    }



    return (
        <div className="flex justify-center p-6">
            <div className="w-full max-w-5xl p-6">

                {/* Heading */}
                <h1 className="text-3xl font-bold text-gray-800 mb-6 text-center">
                    Employee List
                </h1>

                {/* Search Bar */}
                <div className="mb-6 flex justify-start">
                    <input
                        type="text"
                        placeholder="Search employee by name..."
                        value={searchText}
                        onChange={(e) => setSearchText(e.target.value)}
                        className="w-full max-w-[18rem] px-3 py-1 bg-gray-50 border rounded-lg shadow-sm"
                    />
                </div>


                {/* Table */}
                <div className="overflow-x-auto">
                    <table className="w-full  rounded-lg overflow-hidden">

                        {/* Table Header */}
                        <thead className="bg-gradient-to-r from-blue-500 to-indigo-500 text-white">
                            <tr>
                                <th className="py-3 px-4 border-2 border-gray-300 text-left">ID</th>
                                <th className="py-3 px-4 border-2 border-gray-300 text-left">First Name</th>
                                <th className="py-3 px-4 border-2 border-gray-300 text-left">Last Name</th>
                                <th className="py-3 px-4 border-2 border-gray-300 text-left">Email</th>
                                <th className="py-3 px-4 border-2 border-gray-300 text-left">Actions</th>
                            </tr>
                        </thead>

                        {/* Table Body */}
                        <tbody>
                            {
                                filteredEmployees.map((emp, index) => (
                                    <tr
                                        key={emp.id}      //each row should have a unique key value
                                        className={`border-b ${index % 2 !== 0 ? 'bg-gray-200' : 'bg-white'}`}  // putting gray colour on odd number rows
                                    >
                                        <td className="py-3 px-4 border-2 border-gray-300 font-medium text-gray-700">{emp.id}</td>
                                        <td className="py-3 px-4 border-2 border-gray-300">{emp.firstName}</td>
                                        <td className="py-3 px-4 border-2 border-gray-300">{emp.lastName}</td>
                                        <td className="py-3 px-4 border-2 border-gray-300 ">{emp.email}</td>
                                        <td className="py-3 px-4 border-2 border-gray-300 space-x-3">
                                            <button onClick={() => updateEmployee(emp.id)} className="px-4 md:px-4 py-1 text-sm font-medium text-blue-600 border border-blue-600 rounded hover:bg-blue-600 hover:text-white transition cursor-pointer">
                                                Edit
                                            </button>

                                            <button onClick={() => deleteEmployee(emp.id)}  className="px-2 md:px-3 py-1 text-sm font-medium text-red-600 border border-red-600 rounded hover:bg-red-600 hover:text-white transition cursor-pointer">
                                                Delete
                                            </button>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>

                    </table>
                </div>

            </div>
        </div>
    )
}

export default ListAllEmployees