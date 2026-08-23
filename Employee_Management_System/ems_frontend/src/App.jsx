//time :- 1hr 55min

import './App.css'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ListAllEmployees from './components/ListAllEmployees'
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './components/Home';
import AddEmployee from './components/AddEmployee';
import UpdateEmployee from './components/UpdateEmployee';


function App() {

  return (
    <div className='min-h-screen flex flex-col bg-gray-300'>

      <BrowserRouter>
        <Header />
          <div className="flex-grow">
            <Routes>
              {/* http://localhost:3000 */}
              <Route path='/' element={<Home />} />
              {/* http://localhost:3000/employees */}
              <Route path='/employees' element={<ListAllEmployees />} />
              {/* http://localhost:3000/add-employee */}
              <Route path='/add-employee' element={<AddEmployee />} />
              {/* http://localhost:3000/edit-employee/1 */}
              <Route path='/edit-employee/:id' element={<UpdateEmployee/>} />
            </Routes>
          </div>
        <Footer />
      </BrowserRouter>

    </div>
  )
}

export default App
