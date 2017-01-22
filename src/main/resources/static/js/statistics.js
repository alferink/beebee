function createBeehiveStatisticData(beehiveStatistics, field) {

    var data = []
    for (var i = 0; i < beehiveStatistics.length; i++) {
        var statistics = beehiveStatistics[i]
        var beehive = statistics.beehive
        var years = []
        var values = []
        for (var j = 0; j < statistics.beehiveStatistics.length; j++) {
            years.push(statistics.beehiveStatistics[j].year)
            values.push(statistics.beehiveStatistics[j][field])
        }

        console.log('beehive:' + beehive)
        console.log('beehive:' + JSON.stringify(beehive))
        // console.log('name:' + beehive.name)
        data.push({
            x: years,
            y: values,
            type: 'bar',
            marker: {color: beehive.color},
            name: beehive.name,
        });
    }

    return data
}