import React, { useState } from "react";
//import { NativeSelect, FormControl } from "@material-ui/core";
//import styles from "./DataPicker.module.css";
import DatePicker, { CalendarContainer } from "react-datepicker";
//import { fetchCountries } from "../../api";
import "react-datepicker/dist/react-datepicker.css";

const DataPicker = ({ handleDateChange }) => {
  const [startDate, setStartDate] = useState(new Date());
  const MyContainer = ({ className, children }) => {
    return (
      <div
        style={{
          marginLeft: "335%",
        }}
      >
        <CalendarContainer className={className}>
          <div>Pick one day!</div>
          <div style={{ position: "relative" }}>{children}</div>
        </CalendarContainer>
      </div>
    );
  };
  //const [fetchedCountries, setFetchedCountries] = useState([]);
  //useEffect(() => {
  //  const fetchAPI = async () => {
  //    setFetchedCountries(await fetchCountries());
  //  };
  //  fetchAPI();
  //}, [setFetchedCountries]);

  return (
    <DatePicker
      selected={startDate}
      onChange={(date) => setStartDate(date)}
      calendarContainer={MyContainer}
    />
  );
};

export default DataPicker;
