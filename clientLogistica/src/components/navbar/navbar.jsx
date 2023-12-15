import { Link } from "react-router-dom";
import "./Navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="container mx-auto flex justify-between items-center">
        <div>
          <Link to="/" className="logo">
            LuizaLabs & Magalu
          </Link>
        </div>
        <div className="nav-list">
          <Link to="/" className="nav-link">
            Home
          </Link>
          <Link to="/about" className="nav-link">
            Agradecimento
          </Link>
          <div className="dropdown">
            <button className="dropbtn">Consultar dados ▼</button>
            <div className="dropdown-content">
              <Link to="/JsonResponse" className="nav-link">
                Todos
              </Link>
              <Link to="/IdResponse" className="nav-link">
                ID
              </Link>
              <Link to="/DataResponse" className="nav-link">
                Data
              </Link>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
