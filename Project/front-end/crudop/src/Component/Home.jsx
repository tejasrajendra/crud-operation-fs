import React from 'react';
import { Link  } from 'react-router-dom';
import "../Styles/Home.css";


const Home = () =>{

    return(
        <div>
            <>
            <nav>
                <ul>
                    <li> 
                        <Link to = "/add-employee">Add-Employee</Link>
                    </li>
                    <li> 
                        <Link to = "/search">search the employees</Link>
                    </li>
                    
                </ul>
            </nav>
        
            </>
        </div>
    )

}

export default Home