import "./App.css";
import NavBar from "./components/NavBar";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Province from "./components/Pages/Province";
import Region_Date from "./components/Pages/Region_Date";
import Date from "./components/Pages/Date";
import Country from "./components/Pages/Country";

function App() {
  return (
    <>
      <Router>
        <NavBar />

        <div className="pages">
          <Switch>
            <Route exact path="/" component={Country} />
            <Route path="/date" component={Date} />
            <Route path="/region" component={Region_Date} />
            <Route path="/province" component={Province} />
          </Switch>
        </div>
      </Router>
    </>
  );
}

export default App;
