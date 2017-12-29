/*
 * Copyright (c) 2015-2018 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dzikoysk.lightmc;

import net.dzikoysk.lightmc.core.command.BukkitCommandCenter;
import net.dzikoysk.lightmc.util.metrics.MetricsCollector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Light extends JavaPlugin {

    private static Light light;
    private final LightInitializer lightInitializer;

    private final BukkitCommandCenter bukkitCommandCenter;

    public Light() {
        this.lightInitializer = new LightInitializer(this);
        this.bukkitCommandCenter = new BukkitCommandCenter(this);
    }

    @Override
    public void onLoad() {
        light = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, lightInitializer);

        MetricsCollector metricsCollector = new MetricsCollector(this);
        metricsCollector.start();
    }

    @Override
    public void onDisable() {
    }

    public BukkitCommandCenter getBukkitCommandCenter() {
        return bukkitCommandCenter;
    }

    public static Light getInstance() {
        return light;
    }

}
