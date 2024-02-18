import React, { useState } from "react";
import EmployeeService from "../services/EmployeeService";
import { Link } from "react-router-dom";
import "../Styles/SearchResult.css";

export const SearchResult = (props) => {
  const [id, setthisId] = useState(props.id);
  const [firstName, setFirstName] = useState(props.firstName);
  const [lastName, setLastName] = useState(props.lastName);
  const [emailid, setEmailid] = useState(props.emailid);
  const [display, setDisplay] = useState(true);

  const getAllEmployees = () => {
    // EmployeeService.getAllEmployees().then((response) => {
    //     // setEmployees(response.data)
    //     console.log(response.data);
    // }).catch(error =>{
    //     console.log(error);
    // })

    setthisId("");
    setFirstName("");
    setLastName("");
    setEmailid("");
    setDisplay(false);
  };

  const deleteEmployee = (employeeId) => {
    EmployeeService.deleteEmployee(employeeId)
      .then((response) => {
        getAllEmployees();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div>
      <table className="table">
        <thead>
          <td>Id</td>
          <td>FirstName</td>
          <td>LastName</td>
          <td>EmailId</td>
          <td>Action</td>
        </thead>
        <tbody className="tablebody">
          <tr>
            <td className="tabledata">{id}</td>
            <td className="tabledata">{firstName}</td>
            <td className="tabledata">{lastName}</td>
            <td className="tabledata">{emailid}</td>
            <td>
              {display && (
                <div>
                  <Link to={`/edit-employee/${id}`}>Update</Link>
                  <button
                    className="btn btn-danger"
                    onClick={() => deleteEmployee(id)}
                    style={{ marginLeft: "10px" }}
                  >
                    {" "}
                    Delete
                  </button>
                </div>
              )}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};
