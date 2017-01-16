package de.alferink.bee

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import org.springframework.web.context.ContextLoader

@Component
class AppContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext

    public void setApplicationContext(final ApplicationContext context)
            throws BeansException {
        applicationContext = context;
    }

    static ApplicationContext getApplicationContext() {
        applicationContext
    }
}
