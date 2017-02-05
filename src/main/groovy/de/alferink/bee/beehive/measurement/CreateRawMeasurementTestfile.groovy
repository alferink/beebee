package de.alferink.bee.beehive.measurement

import java.util.concurrent.ThreadLocalRandom

class CreateRawMeasurementTestfile {


    static main(def args) {

//        def beehiveIds = ['4028b88159c7b8120159c7c113770014', '4028b88159c65c9a0159c660c3ab0001', '4028b88159e5864c0159e5e511b80000']
        def beehiveIds = ['2c9fbb0159b855e90159b858c3d90001', '2c9fbb0159b855e90159b86c33910084', '2c9fbb0159c84c560159cd09b3560061']

        def file = new File('C:\\dev\\projects\\beebee\\measurement-test-beebee1.csv')

        file << "beehiveId,date,temperatureOutside,temperatureInside,humidityOutside,weight\n"

        beehiveIds.each{ String beehiveId ->

            def date = new Date() - 365
            def temperatureOutside = 20
            def temperatureInside = 37
            def humidityOutside = 60
            def weight = 40

            365.times {
                date = date + 1
                temperatureOutside = Math.max(-20, Math.min(35.0, temperatureOutside + ThreadLocalRandom.current().nextDouble(-2, 2)))
                temperatureInside = Math.max(20, Math.min(42.0, temperatureInside + ThreadLocalRandom.current().nextDouble(-1, 1)))
                humidityOutside = Math.max(30, Math.min(99.0, humidityOutside + ThreadLocalRandom.current().nextDouble(-3, 3)))
                weight = Math.max(20, Math.min(80.0, weight  + ThreadLocalRandom.current().nextDouble(-1, 1)))

                String formattedDate = date.format("yyyy-MM-dd'T'HH:mm:ss.SSSZ")

                file << "$beehiveId,$formattedDate,$temperatureOutside,$temperatureInside,$humidityOutside,$weight\n"
            }
        }
    }
}
