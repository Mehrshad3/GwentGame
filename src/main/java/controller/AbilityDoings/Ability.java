package controller.AbilityDoings;

public abstract class Ability {
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void DoCardAbility() ;
}
