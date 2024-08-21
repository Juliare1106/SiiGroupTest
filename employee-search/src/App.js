
import React from "react";
import EmployeeSearch from './components/EmployeeSearch';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<EmployeeSearch />} />
        <Route path="/employees" element={<EmployeeSearch />} />
      </Routes>
    </Router>
  );
}

export default App;
