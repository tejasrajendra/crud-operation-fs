import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import EmployeeService from "../services/EmployeeService";
import "../Styles/AddEmployeeComponent.css";

const AddEmployeeComponent = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [emailId, setEmailId] = useState("");
  const history = useNavigate();
  const { id } = useParams();

  const saveOrUpdateEmployee = (e) => {
    e.preventDefault();

    const employee = { firstName, lastName, emailId };

    if (id) {
      EmployeeService.updateEmployee(id, employee)
        .then((response) => {
          history.push("/employees");
        })
        .catch((error) => {
          console.log(error);
        });

      alert("Employee data updated sucessfully");
    } else {
      EmployeeService.createEmployee(employee)
        .then((response) => {
          console.log(response.data);

          history.push("/employees");
        })
        .catch((error) => {
          console.log(error);
        });
      alert("Employee data submitted sucessfully");
    }
  };

  useEffect(() => {
    EmployeeService.getEmployeeById(id)
      .then((response) => {
        setFirstName(response.data.firstName);
        setLastName(response.data.lastName);
        setEmailId(response.data.emailId);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const title = () => {
    if (id) {
      return <h2>Update Employee</h2>;
    } else {
      return <h2>Add Employee</h2>;
    }
  };

  return (
    <div>
      <br />
      <br />
      <div>
        <div>
          <div>
            {title()}
            <div>
              <form>
                <div>
                  <label> First Name :</label>
                  <input
                    type="text"
                    placeholder="Enter first name"
                    name="firstName"
                    className="form-control"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                  ></input>
                </div>

                <div>
                  <label> Last Name :</label>
                  <input
                    type="text"
                    placeholder="Enter last name"
                    name="lastName"
                    className="form-control"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                  ></input>
                </div>

                <div>
                  <label> Email Id :</label>
                  <input
                    type="email"
                    placeholder="Enter email Id"
                    name="emailId"
                    className="form-control"
                    value={emailId}
                    onChange={(e) => setEmailId(e.target.value)}
                  ></input>
                </div>

                <button onClick={(e) => saveOrUpdateEmployee(e)}>
                  Submit{" "}
                </button>
                <Link to="/"> Cancel </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddEmployeeComponent;
