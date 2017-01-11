package de.alferink.bee.beehive.action

enum BeehiveActionType {

    FEEDING(Feeding, 'feeding'),
    HONEY_HARVEST(HoneyHarvest, 'honeyHarvest'),
    REVIEW(Review, 'review'),
    VARROA_CHECK(VarroaCheck, 'varroaCheck'),
    VARROA_TREATMENT(VarroaTreatment, 'varroaTreatment'),
    QUEEN_INSTALLATION(QueenInstallation, 'queenInstallation'),
    MOVE_BEEHIVE(MoveBeehive, 'moveBeehive'),

    final Class<? extends BeehiveAction> actionClass
    final String propertyName

    BeehiveActionType(Class<? extends BeehiveAction> actionClass, String propertyName) {
        this.actionClass = actionClass
        this.propertyName = propertyName
    }

    String getUrl() {
        propertyName.toLowerCase()
    }
}
