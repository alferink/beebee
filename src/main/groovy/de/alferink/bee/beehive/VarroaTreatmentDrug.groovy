package de.alferink.bee.beehive

import de.alferink.bee.MessageUtils

enum VarroaTreatmentDrug {
    AMEISENSAEURE_60,
    MILCHSAEURE,
    OXALSAEURE

    String getInstanceName(){
        MessageUtils.getInstanceNameMessage(this)
    }

    @Override
    String toString() {
        instanceName
    }
}