package at.ac.uibk.pm.g06.csaz9837.midterm1.e01;

public class Defender extends OutfieldPlayer {
    private int defensiveAbilities;

    public Defender(String name, Position position, int defensiveAbilities) {
        super(name, position);
        this.defensiveAbilities = defensiveAbilities;
    }

    public int getDefensiveAbilities() {
        return defensiveAbilities;
    }

    public void setDefensiveAbilities(int defensiveAbilities) {
        this.defensiveAbilities = defensiveAbilities;
    }

    @Override
    public String toString() {
        return "Defender : " + super.getName() + " , Position - " + super.getPosition() + " , Defending Abilities: " + getDefensiveAbilities();
    }
}
