import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./Component/Home";
import AddEmployeeComponent from "./Component/AddEmployeeComponent";
import Search from "./Component/Search";

function App() {
  return (
    <div>
      <div>
        <Routes>
          {/* <Route  path="/" element = {<ListEmployeeComponent/>}></Route> */}
          <Route index element={<Home />}></Route>
          <Route
            path="/edit-employee/:id"
            element={<AddEmployeeComponent />}
          ></Route>
          <Route
            path="/add-employee"
            element={<AddEmployeeComponent />}
          ></Route>
          <Route path="/search" element={<Search />}></Route>
        </Routes>
      </div>
    </div>
  );
}

export default App;
