import React from "react";

const Footer = () => {
  return (
    <footer className="bg-gray-900 text-gray-300 mt-10">
      
      <div className="px-8 xl:px-28 py-5 flex justify-between items-center">
      

        {/* Left */}
        <div className="text-left">
          <h4 className="text-sm sm:text-md font-medium text-gray-200">
            StaffTrack – Employee Management System
          </h4>
          <p className="text-xs sm:text-sm">
            © {new Date().getFullYear()} All rights reserved
          </p>
        </div>

        {/* Right */}
        <div className="text-md text-left ">
          <p className="text-gray-400 text-sm sm:font-medium">Developed by</p>
          <p className="text-gray-200 font-medium sm:font-semibold">Naman Patel</p>
        </div>

      </div>

    </footer>
  );
};

export default Footer;