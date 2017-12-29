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

package net.dzikoysk.lightmc.util.metrics;

import net.dzikoysk.lightmc.Light;
import org.bukkit.Bukkit;

public class MetricsCollector implements Runnable {

    private final Light light;
    private final Metrics metrics;

    public MetricsCollector(Light light) {
        this.light = light;
        this.metrics = new Metrics(light);
    }

    public void start() {
        Bukkit.getScheduler().runTaskLaterAsynchronously(light, this, 20L);
    }

    @Override
    public void run() {
        Metrics.Graph global = metrics.createGraph("Scripts");
        global.addPlotter(new Metrics.Plotter("Scripts") {
            @Override
            public int getValue() {
                return light.getLightCore().getScripts().size();
            }
        });
        metrics.start();
    }

}
