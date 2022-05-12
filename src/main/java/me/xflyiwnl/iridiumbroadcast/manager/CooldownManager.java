package me.xflyiwnl.iridiumbroadcast.manager;

import me.xflyiwnl.iridiumbroadcast.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

     /*
            Хэшмэп да
     */

    private static Map<UUID, Integer> cooldownTime = new HashMap<UUID, Integer>();

    /*
            Конструктор и запуск раннейбла
     */

    public CooldownManager() {
        runnable();
    }

    /*
            Раннейбл
     */

    public void runnable() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for (UUID uuid : cooldownTime.keySet()) {

                    if (cooldownTime.get(uuid) == null) {
                        return;
                    }

                    if (cooldownTime.get(uuid) == 0) {

                        cooldownTime.remove(uuid);

                        return;
                    }

                    cooldownTime.put(uuid, cooldownTime.get(uuid) - 1);

                }

            }
        }.runTaskTimer(Main.getMain(), 0, 20);

    }

    /*
            Геттеры
     */

    public static Map<UUID, Integer> getCooldownTimer() {
        return cooldownTime;
    }

}
