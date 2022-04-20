import axios from "axios";

const url = "https://covid19.mathdro.id/api";

export const fetchData = async (country) => {
  let changeableUrl = url;
  if (country) {
    changeableUrl = `${url}/countries/${country}`;
  }

  try {
    const options = {
      method: "GET",
      url: "https://covid-19-statistics.p.rapidapi.com/reports",
      params: { region_name: `${country}` },
      headers: {
        "X-RapidAPI-Host": "covid-19-statistics.p.rapidapi.com",
        "X-RapidAPI-Key": "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34",
      },
    };
    axios
      .request(options)
      .then(function (response) {
        console.log(response.data);
        console.log(response.data);
        confirmed = response.data.data[0]["confirmed"];
        recovered = response.data.data[0]["recovered"];
        deaths = response.data.data[0]["deaths"];
        lastUpdate = response.data.data[0]["last_update"];
        console.log(confirmed);
        console.log(recovered);
        console.log(deaths);
        console.log(lastUpdate);
      })
      .catch(function (error) {
        console.error(error);
      });

    console.log("LOL - ", await axios.get(changeableUrl));
    const {
      data: { confirmed, recovered, deaths, lastUpdate },
    } = await axios.get(changeableUrl);
    return {
      confirmed,
      recovered,
      deaths,
      lastUpdate,
    };
  } catch (error) {
    console.log(error);
  }
};

export const fetchDailyData = async () => {
  try {
    const { data } = await axios.get(`${url}/daily`);
    const modifiedData = data.map((dailyData) => ({
      confirmed: dailyData.confirmed.total,
      deaths: dailyData.deaths.total,
      date: dailyData.reportDate,
    }));
    return modifiedData;
  } catch (error) {}
};

export const fetchCountries = async () => {
  try {
    const {
      data: { countries },
    } = await axios.get(`${url}/countries`);
    return countries.map((country) => country.name);
  } catch (error) {
    console.log(error);
  }
};

export const fetchByDate = async (date) => {
  try {
    const options = {
      method: "GET",
      url: "https://covid-19-statistics.p.rapidapi.com/reports/total",
      params: { date: `${date}` },
      headers: {
        "X-RapidAPI-Host": "covid-19-statistics.p.rapidapi.com",
        "X-RapidAPI-Key": "fb7bb9a35emshc06230b446762ddp1ed435jsn883017bf1d34",
      },
    };

    axios
      .request(options)
      .then(function (response) {
        console.log(response.data);
      })
      .catch(function (error) {
        console.error(error);
      });

    const {
      data: { countries },
    } = await axios.get(`${url}/countries`);
    return countries.map((country) => country.name);
  } catch (error) {
    console.log(error);
  }
};
