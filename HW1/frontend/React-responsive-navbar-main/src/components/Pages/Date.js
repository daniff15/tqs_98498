import React from "react";
import Cards from "../Cards/Cards";
import DatePicker from "../DatePicker/DataPicker";
import { fetchData } from "../../api";

class Date extends React.Component {
  state = {
    data: {},
    country: "",
  };

  async componentDidMount() {
    const fetchedData = await fetchData();
    this.setState({ data: fetchedData });
  }

  handleCountryChange = async (country) => {
    const fetchedData = await fetchData(country);
    this.setState({ data: fetchedData, country: country });
  };

  render() {
    const { data, country } = this.state;
    return (
      <div>
        <DatePicker handleCountryChange={this.handleCountryChange} />
        <Cards data={data} country={country} />
      </div>
    );
  }
}

export default Date;
