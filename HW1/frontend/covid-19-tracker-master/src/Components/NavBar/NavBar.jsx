import React from "react";
import {
  AppBar,
  Toolbar,
  CssBaseline,
  Typography,
  makeStyles,
} from "@material-ui/core";
import { Link } from "react-router-dom";
import coronaImage from "../../images/image.png";

const useStyles = makeStyles((theme) => ({
  navlinks: {
    marginLeft: theme.spacing(10),
    display: "flex",
  },
  logo: {
    flexGrow: "1",
    cursor: "pointer",
  },
  link: {
    textDecoration: "none",
    color: "white",
    fontSize: "12px",
    marginLeft: theme.spacing(20),
    "&:hover": {
      color: "yellow",
      borderBottom: "1px solid white",
    },
  },
  image: {
    width: "200px",
  },
}));

function Navbar() {
  const classes = useStyles();

  return (
    <AppBar position="static">
      <CssBaseline />
      <Toolbar>
        <Typography variant="h4" className={classes.logo}>
          <img className={classes.image} src={coronaImage} alt="COVID-19" />
        </Typography>
        <div className={classes.navlinks}>
          <Link to="/" className={classes.link}>
            Search Country
          </Link>
          <Link to="/" className={classes.link}>
            Search Date
          </Link>
          <Link to="/" className={classes.link}>
            Search Region in certain Date
          </Link>
          <Link to="/" className={classes.link}>
            Search Province in Country certain Date
          </Link>
        </div>
      </Toolbar>
    </AppBar>
  );
}
export default Navbar;
