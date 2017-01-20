package de.alferink.bee

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

class MessageUtils {

    static String getInstanceNameMessage(Enum value) {
        MessageSource messageSource = AppContext.applicationContext.getBean(MessageSource)
        messageSource?.getMessage("${toPropertyName(value.class.simpleName)}.${value.name()}.label", [] as Object[], value.name(), LocaleContextHolder.locale) ?: value.name()
    }

    static String getInstanceNameMessage(Object value, Object... params) {
        MessageSource messageSource = AppContext.applicationContext.getBean(MessageSource)
        messageSource?.getMessage("${toPropertyName(value.class.simpleName)}.instanceName.label", params, LocaleContextHolder.locale) ?: value.class.simpleName
    }

    static toPropertyName(String name) {
        if(name.length() > 1) {
            name.substring(0,1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        } else {
            name
        }
    }

}
