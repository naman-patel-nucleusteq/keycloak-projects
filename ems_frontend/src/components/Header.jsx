import React from 'react';
import { Link } from 'react-router-dom';
import { doLogout, getUsername } from '../services/keycloak'; 

function Header() {
  const username = getUsername(); 

  return (
    <nav className="bg-slate-900 text-white shadow-md border-b border-slate-800">
      <div className="max-w-7xl mx-auto px-4 py-3 flex items-center justify-between">
        
        <div className="flex items-center gap-6">
          <Link to="/" className="text-xl font-bold tracking-wide text-indigo-400 hover:text-indigo-300 transition">
            Employee Management System
          </Link>
          <Link to="/employees" className="text-sm font-medium text-slate-300 hover:text-white transition">
            Employees List
          </Link>
          <Link to="/add-employee" className="text-sm font-medium text-slate-300 hover:text-white transition">
            Add Employee
          </Link>
        </div>

        <div className="flex items-center gap-6">
          {username && (
            <span className="text-sm font-medium text-slate-400 hidden sm:inline">
              Welcome, <strong className="text-indigo-200">{username}</strong>
            </span>
          )}
          
          <button 
            onClick={doLogout} 
            className="cursor-pointer rounded-lg bg-rose-600 px-4 py-2 text-sm font-semibold text-white shadow-sm transition-all hover:bg-rose-500 hover:shadow-rose-900/20 active:scale-95"
          >
            Sign Out
          </button>
        </div>

      </div>
    </nav>
  );
}

export default Header;
