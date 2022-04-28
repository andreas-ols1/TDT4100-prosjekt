package PokemonShowdown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class PokemonTest {
    
    private Pokemon charizard;


    @BeforeEach
    public void setup() {
        charizard = new Pokemon("Charizard");
    }
    
    @Test
    @DisplayName("Test constructor")
    public void testConstructor() {
        assertEquals("Charizard",charizard.getName());
        IntStream.range(0, 1).forEach(i -> {
            assertEquals(Arrays.asList("Fire","Flying").get(i), charizard.getTypes().get(i).getName());
        });
        assertEquals(328, charizard.getHp());
        assertEquals(285, charizard.getAttack());
        assertEquals(237, charizard.getDefence());
        assertEquals(267, charizard.getSpeed());
        IntStream.range(0,4).forEach(i -> {
            assertEquals(Arrays.asList("Flamethrower","Earthquake","Swords Dance","Air Slash").get(i), charizard.getMove(i).getName());
        });
    }

    @Test
    @DisplayName("Test revertStatChanges")
    public void testRevertStatChanges() {
        Pokemon gyarados = new Pokemon("Gyarados");
        gyarados.attack(charizard, 2);
        assertEquals(1.5, gyarados.getAttackBoost());
        assertEquals(1.5, gyarados.getSpeedBoost());
        gyarados.revertStatChanges();
        assertEquals(gyarados.getOrgAttack(), gyarados.getAttack());
        assertEquals(gyarados.getOrgSpeed(), gyarados.getSpeed());
        assertEquals(1, gyarados.getAttackBoost());
        assertEquals(1, gyarados.getSpeedBoost());

    }

    @Test
    @DisplayName("Test non-damaging attacks")
    public void testPassiveAttacks() {
        Pokemon dragonite = new Pokemon("Dragonite");
        IntStream.range(0, 8).forEach(i -> {
            dragonite.attack(charizard, 3);
            assertEquals((int)(dragonite.getOrgAttack()*dragonite.getAttackBoost()), dragonite.getAttack());
            assertEquals((int)(dragonite.getOrgSpeed()*dragonite.getSpeedBoost()), dragonite.getSpeed());
        });
        assertEquals(charizard.getMaxHp(), charizard.getHp());
        Pokemon rayquaza = new Pokemon("Rayquaza");
        IntStream.range(0, 2).forEach(i -> {
            rayquaza.attack(charizard, 2);
            assertEquals((int) Math.floor(rayquaza.getOrgAttack()*rayquaza.getAttackBoost()), rayquaza.getAttack());
            assertEquals((int) Math.floor(rayquaza.getOrgSpeed()*rayquaza.getSpeedBoost()), rayquaza.getSpeed());
            charizard.setHp(charizard.getMaxHp());
        });
        dragonite.setHp(50);
        dragonite.attack(rayquaza, 2);   
        assertEquals(50+dragonite.getMaxHp()*0.5, dragonite.getHp());
        dragonite.setHp(300);
        dragonite.attack(rayquaza, 2);   
        assertEquals(dragonite.getMaxHp(), dragonite.getHp());
    }

    @RepeatedTest(10000)
    @DisplayName("Test attack()")
    public void testAttack() {
        Pokemon amoonguss = new Pokemon("Amoonguss");
        assertCorrectDamage(amoonguss, charizard, charizard.getMaxHp(), 0, 1.5, 0.25);
        
        Pokemon gyarados = new Pokemon("Gyarados");
        assertCorrectDamage(charizard, gyarados, gyarados.getMaxHp(), 0, 1.5, 0.5);
        
        assertCorrectDamage(gyarados, charizard, charizard.getMaxHp(), 1, 1, 1);
 
        assertCorrectDamage(gyarados, charizard, charizard.getMaxHp(), 3, 1, 2);

        Pokemon pikachu = new Pokemon("Pikachu");
        assertCorrectDamage(pikachu, gyarados, 200, 1, 1.5, 4);
        
        gyarados.setHp(gyarados.getMaxHp());
        charizard.attack(gyarados, 1);
        assertTrue(gyarados.getHp() == gyarados.getMaxHp());
    }
    

    private void assertCorrectDamage(Pokemon attacker, Pokemon defender, int defenderHp ,int moveIndex, double stab, double effectiveness) {
        defender.setHp(defenderHp);
        attacker.attack(defender, moveIndex);
        double moveBaseDamage = attacker.getMove(moveIndex).getDamage();
        double attackPerOpponentsDefence = (double)attacker.getAttack()/defender.getDefence();
        int minDamage = (int)Math.floor(((0.84*moveBaseDamage*attackPerOpponentsDefence)+2)*stab*effectiveness*0.85);
        int maxDamage = (int)Math.floor(((0.84*moveBaseDamage*attackPerOpponentsDefence)+2)*stab*effectiveness*1.95);
        if (!defender.isDead()) assertTrue(defenderHp-minDamage >= defender.getHp() && defender.getHp() >= defenderHp-maxDamage);
        if (defenderHp-maxDamage <= 0 && defenderHp - minDamage <= 0) assertEquals(0, defender.getHp());
        if (defenderHp-maxDamage <= 0 && defenderHp - minDamage > 0) assertTrue(defenderHp-minDamage >= defender.getHp() && defender.getHp() >= defenderHp-maxDamage);
    }

}
