import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="flex flex-col items-center justify-center h-[70vh] text-center">

      <h1 className="text-3xl font-bold text-gray-800 mb-4">
        Welcome to StaffTrack
      </h1>

      <p className="text-lg text-gray-600 mb-8">
        Manage employees efficiently with ease.
      </p>

      <div className="flex flex-col gap-6">
        
        {/* List Employees Button */}
        <button
          onClick={() => navigate("/employees")}
          className="text-lg bg-blue-600 hover:bg-blue-700 hover:cursor-pointer text-white px-6 py-3 rounded-lg shadow-md transition"
        >
          List All Employees
        </button>

        {/* Add Employee Button */}
        <button
          onClick={() => navigate("/add-employee")}
          className="text-lg bg-green-600 hover:bg-green-700 hover:cursor-pointer text-white px-6 py-3 rounded-lg shadow-md transition"
        >
          Add Employee
        </button>

      </div>

    </div>
  );
};

export default Home;