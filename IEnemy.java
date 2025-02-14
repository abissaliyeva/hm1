
package enemies;

import player.IDamageable;

public interface IEnemy extends IDamageable {
    int getAttackPower();
    int getPoints();
}