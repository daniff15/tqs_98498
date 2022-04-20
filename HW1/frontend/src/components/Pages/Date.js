import React from "react";
import Cards from "../Cards/Cards";
import DatePicker from "../DatePicker/DataPicker";
import { fetchByDate } from "../../api";

class Date extends React.Component {
  state = {
    data: {},
    country: "",
  };

  handleDateChange = async (date) => {
    const fetchedData = await fetchByDate(date);
    this.setState({ data: fetchedData, date: date });
  };

  render() {
    const { data, country } = this.state;
    return (
      <div>
        <DatePicker handleDateChange={this.handleDateChange} />
        <Cards data={data} country={country} />
      </div>
    );
  }
}

export default Date;
