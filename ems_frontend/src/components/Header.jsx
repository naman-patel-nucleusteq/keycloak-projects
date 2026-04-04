import React from "react";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <header className="bg-gray-900 text-white shadow-md">
      <div className="max-w-7xl mx-auto px-6 py-4 grid grid-cols-2 sm:grid-cols-3 items-center">
        
        {/* Left - Logo */}
        <h1 className="text-2xl font-bold tracking-wide xl:-mx-12">
          StaffTrack
        </h1>

        {/* Center - Nav Links */}
        <nav className="flex justify-center text-nowrap space-x-6 text-[14px] md:text-[16px]">
          <Link to="/" className="hover:font-semibold transition">Home</Link>
          <Link to="/employees" className="hover:font-semibold transition">Employees</Link>
          <Link to="/add-employee" className="hover:font-semibold transition">Add Employee</Link>
        </nav>

        {/* Right - Empty / Future */}
        <div className="flex justify-end">
          
        </div>

      </div>
    </header>
  );
};

export default Header;