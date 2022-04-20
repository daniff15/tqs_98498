import React from "react";
import Cards from "../Cards/Cards";
import CountryPicker from "../CountryPicker/CountryPicker";
import { fetchData } from "../../api";

class Country extends React.Component {
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
        <CountryPicker handleCountryChange={this.handleCountryChange} />
        <Cards data={data} country={country} />
      </div>
    );
  }
}

export default Country;
