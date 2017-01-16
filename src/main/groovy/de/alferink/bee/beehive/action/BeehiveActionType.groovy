package de.alferink.bee.beehive.action

enum BeehiveActionType {

    FEEDING(Feeding, 'feeding'),
    HONEY_HARVEST(HoneyHarvest, 'honeyHarvest'),
    REVIEW(Review, 'review'),
    VARROA_CHECK(VarroaCheck, 'varroaCheck'),
    VARROA_TREATMENT(VarroaTreatment, 'varroaTreatment'),
    QUEEN_INSTALLATION(QueenInstallation, 'queenInstallation'),
    MOVE_BEEHIVE(MoveBeehive, 'moveBeehive'),
    CREATION(MoveBeehive, 'creation')

    final Class<? extends ChangeBeehiveAction> actionClass
    final String propertyName

    BeehiveActionType(Class<? extends ChangeBeehiveAction> actionClass, String propertyName) {
        this.actionClass = actionClass
        this.propertyName = propertyName
    }

    String getUrl() {
        propertyName.toLowerCase()
    }

    static List<BeehiveActionType> getChangeBeehiveActionType() {
        values() - CREATION
    }
}
