package de.alferink.bee

import java.time.LocalDate
import java.time.ZoneId

class DateConverterUtils {

    static LocalDate toLocalDate(Date date) {
        date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    }
    static Date toDate(LocalDate localDate) {
        Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
    }
}
