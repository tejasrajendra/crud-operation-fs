import React, {  useState } from 'react';
import {FaSearch} from "react-icons/fa"
import EmployeeService from '../services/EmployeeService';
import "../Styles/SearchBar.css"

export const SearchBar = (props) => {
  const [input, setInput] = useState("");
  // const {id , setId} = useState()
  // const [firstName, setFirstName] = useState('')
  // const [lastName, setLastName] = useState('')
  // const [emailId, setEmailId] = useState('')

  const fetchData = (id) => {
    EmployeeService.getEmployeeById(id)
      .then((response) => {
        console.log(response.data);
        console.log("something is nice " + response.data.id);
        // setFirstName(response.data.firstName)
        // setLastName(response.data.lastName)
        // setEmailId(response.data.emailId)
        const id = response.data.id;
        const firstName = response.data.firstName

        props.setTheValue(
          response.data.id,
          response.data.firstName,
          response.data.lastName,
          response.data.emailId
        );
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleChange = (value) => {
    console.log("this is inside the handle change function ");
    setInput(value);
    if (value.length === 0) {
      console.log("this is the length of the string " + value.length);
      props.setDisplay();
    } else {
      console.log("this is inside the ");
      fetchData(value);
    }
  };

  return (
    <div>
       <h1> Search By Employee-Id </h1>
      <FaSearch id="search-icon" />
      <input
        placeholder="Type to search..."
        value={input}
        onChange={(e) => {
          handleChange(e.target.value);
        }}
      />
      {/* <button  onClick={handleSubmit}>submit</button>  */}
    </div>
  );
};