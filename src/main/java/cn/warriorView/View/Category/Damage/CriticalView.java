package cn.warriorView.View.Category.Damage;

import cn.warriorView.Util.ViewUtil;
import cn.warriorView.View.ViewParams;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class CriticalView extends DamageOtherView {

    public CriticalView(ViewParams params) {
        super(params);
    }

    @Override
    public void spawn(EntityDamageByEntityEvent event, double damage) {
        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            Projectile attacker = (Projectile) event.getDamager();
            Player player = null;
            if (attacker.getShooter() instanceof Player p) {
                player = p;
            } else {
                if (this.onlyPlayer) return;
            }
            LivingEntity entity = (LivingEntity) event.getEntity();
            Location damageLocation = switch (this.position) {
                case DAMAGE -> attacker.getLocation().add(attacker.getVelocity());
                case EYE -> entity.getEyeLocation();
                default -> entity.getLocation();
            };
            ViewUtil.spawnDisplay(IAnimation, shadow, viewRange, viewMarge, seeThrough, textFormat, textOpacity, backgroundColor, scale, damageLocation, player, damage);
            return;
        }
        super.spawn(event, damage);
    }
}
