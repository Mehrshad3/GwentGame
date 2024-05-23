package model.faction;

class CardAbilityPerformer {
    private final CardAbilityPerformer previousAction;
    private final NonCommanderCardSingleAbility newAbility;

    public CardAbilityPerformer(CardAbilityPerformer previousAction, NonCommanderCardSingleAbility newAbility) {
        this.previousAction = previousAction;
        this.newAbility = newAbility;
    }

    public void doAction() {
        previousAction.doAction();
        newAbility.doAction();
    }
}
