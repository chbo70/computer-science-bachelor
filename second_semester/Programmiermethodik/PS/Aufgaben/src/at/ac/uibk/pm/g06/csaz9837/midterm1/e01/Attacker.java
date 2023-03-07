package at.ac.uibk.pm.g06.csaz9837.midterm1.e01;

public class Attacker extends OutfieldPlayer {
    private int attackingAbilities;

    public Attacker(String name, Position position, int attackingAbilities) {
        super(name, position);
        this.attackingAbilities = attackingAbilities;
    }

    public int getAttackingAbilities() {
        return attackingAbilities;
    }

    public void setAttackingAbilities(int attackingAbilities) {
        this.attackingAbilities = attackingAbilities;
    }

    @Override
    public String toString() {
        return "Attacker : " + super.getName() + " , Position - " + super.getPosition() + " , Attacking Abilities: " + getAttackingAbilities();
    }
}
