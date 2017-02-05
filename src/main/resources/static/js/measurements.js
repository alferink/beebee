
function createMeasurementsDiagram(elementId, aggregatedBeehiveMeasurements) {

    var dates = [];
    var weights = [];
    var temperaturesInside = [];
    var temperaturesOutside = [];
    var humiditiesOutside = [];

    for (var i = 0; i < aggregatedBeehiveMeasurements.length; i++) {
        dates.push(aggregatedBeehiveMeasurements[i].date);
        weights.push(aggregatedBeehiveMeasurements[i].weight);
        temperaturesInside.push(aggregatedBeehiveMeasurements[i].temperatureInside);
        temperaturesOutside.push(aggregatedBeehiveMeasurements[i].temperatureOutside);
        humiditiesOutside.push(aggregatedBeehiveMeasurements[i].humidityOutside);
    }

    var weightsData = {
        x: dates,
        y: weights,
        mode: 'lines',
        type: 'bar',
        name: 'Gewicht'
    };
    var temperaturesInsideData = {
        x: dates,
        y: temperaturesInside,
        mode: 'lines',
        type: 'line',
        yaxis: 'y2',
        name: 'Innentemperatur'
    };
    var temperaturesOutsideData = {
        x: dates,
        y: temperaturesOutside,
        mode: 'lines',
        type: 'line',
        yaxis: 'y2',
        name: 'Außentemperatur'
    };
    var humiditiesOutsideData = {
        x: dates,
        y: humiditiesOutside,
        mode: 'lines',
        type: 'line',
        yaxis: 'y3',
        name: 'Luftfeuchte'
    };

    var data = [weightsData, temperaturesInsideData, temperaturesOutsideData, humiditiesOutsideData];

    var layout = {
        xaxis: {
            domain: [0.0, 0.95],
            type: 'date'
        },
        yaxis: {
            tickvals:[0, 10, 20, 30, 40, 50, 60, 70, 80],
            ticktext : ['0 kg', '10 kg', '20 kg', '30 kg', '40 kg', '50 kg', '60 kg', '70 kg', '80 kg'],
        },
        yaxis2: {
            tickvals:[-20, -10, 0, 10, 20, 30, 40],
            ticktext : ['-20 °C', '-10 °C', '0 °C', '10 °C', '20 °C', '30 °C', '40 °C'],
            titlefont: {color: 'rgb(148, 103, 189)'},
            tickfont: {color: 'rgb(148, 103, 189)'},
            anchor: 'x',
            overlaying: 'y',
            side: 'right'
        },
        yaxis3: {
            tickvals:[30, 40, 50, 60, 70, 80, 90, 100],
            ticktext : ['30 %', '40 %', '50 %', '60 %', '70 %', '80 %', '90 %', '100 %'],
            titlefont: {color: 'rgb(148, 103, 189)'},
            tickfont: {color: 'rgb(148, 103, 189)'},
            anchor: 'free',
            overlaying: 'y',
            side: 'right',
            position: 1.0
        },
        legend: {
            "orientation": "h",
            x: 0,
            y: -0.2
        }
    };

    Plotly.plot(elementId, data, layout);
}
