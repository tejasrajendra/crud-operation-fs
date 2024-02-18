import React, { useState } from "react";
import { SearchBar } from "./SearchBar";
import { SearchResult } from "./SearchResult.jsx";
import "../Styles/Search.css";

function Search() {
  const [id, setthisId] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [emailid, setEmailid] = useState("");
  const [toDisplay, setDisplay] = useState(false);

  const setttingThevalue = (id, firstName, lastName, emailid) => {
    console.log("inside the settingTheValue " + firstName);

    if (firstName.length === 0) {
      setDisplay(false);
    } else {
      setthisId(id);
      setFirstName(firstName);
      setLastName(lastName);
      setEmailid(emailid);
      console.log(firstName.length);
      setDisplay(true);
    }
  };

  const handleDisplay = () => {
    console.log("this is the handleDisplay ");
    setDisplay(false);
  };

  return (
    <div>
      <SearchBar setTheValue={setttingThevalue} setDisplay={handleDisplay} />

      {toDisplay && (
        <div>
          <SearchResult
            id={id}
            firstName={firstName}
            lastName={lastName}
            emailid={emailid}
          />
        </div>
      )}
    </div>
  );
}

export default Search;
