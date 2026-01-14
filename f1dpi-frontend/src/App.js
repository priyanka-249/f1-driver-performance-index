import { useEffect, useState } from "react";

function App() {
  const [drivers, setDrivers] = useState([]);
  const [sortBy, setSortBy] = useState("dpi");

  useEffect(() => {
    fetch("http://localhost:8080/drivers")
        .then((res) => res.json())
        .then((data) => setDrivers(data));
  }, []);

  const sortedDrivers = [...drivers].sort((a, b) => {
    if (sortBy === "reliability") {
      return (b.racesFinished / b.totalRaces) -
          (a.racesFinished / a.totalRaces);
    }
    return b.dpi - a.dpi;
  });

  return (
      <div style={{ padding: "20px" }}>
        <h2>F1 Driver Performance Index (2025)</h2>

        <div style={{ marginBottom: "12px" }}>
          <button onClick={() => setSortBy("dpi")}>
            Sort by DPI
          </button>
          <button
              onClick={() => setSortBy("reliability")}
              style={{ marginLeft: "8px" }}
          >
            Sort by Reliability
          </button>
        </div>

        <table border="1" cellPadding="8">
          <thead>
          <tr>
            <th>Driver</th>
            <th>Team</th>
            <th>Points</th>
            <th>DPI</th>
            <th>Finish Rate</th>
          </tr>
          </thead>
          <tbody>
          {sortedDrivers.map((d, index) => (
              <tr key={index}>
                <td>{d.name}</td>
                <td>{d.team}</td>
                <td>{d.points}</td>
                <td>{d.dpi.toFixed(1)}</td>
                <td>
                  {Math.round((d.racesFinished / d.totalRaces) * 100)}%
                </td>
              </tr>
          ))}
          </tbody>
        </table>
      </div>
  );
}

export default App;
