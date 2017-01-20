package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum VarroaTreatmentType {
    SCHWAMMTUCH_OBEN,
    TRAEUFELMETHODE

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}