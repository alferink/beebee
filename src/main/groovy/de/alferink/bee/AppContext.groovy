package de.alferink.bee

import org.springframework.context.ApplicationContext
import org.springframework.web.context.ContextLoader

class AppContext {

    private static ApplicationContext applicationContext

    public ApplicationContext getApplicationContext() {
        if(applicationContext == null) {
            applicationContext = ContextLoader.currentWebApplicationContext
        }
        applicationContext
    }
}
