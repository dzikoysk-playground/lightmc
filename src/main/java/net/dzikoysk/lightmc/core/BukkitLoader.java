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

package net.dzikoysk.lightmc.core;

import net.dzikoysk.lightmc.Light;
import org.panda_lang.light.LightCore;

import java.io.File;

public class BukkitLoader {

    private final Light light;
    private final File directory;

    public BukkitLoader(Light light, File directory) {
        this.light = light;
        this.directory = directory;
    }

    public void load() {
        if (!directory.exists()) {
            directory.mkdir();
            return;
        }

        File[] files = directory.listFiles();
        if (files == null || files.length == 0) {
            return;
        }

        LightCore lightCore = light.getLightCore();
        LightLoader lightLoader = lightCore.getLightLoader();

        for (File file : files) {
            LightScript lightScript = lightLoader.load(file);
            lightCore.registerScript(lightScript);
        }
    }

    public File getDirectory() {
        return directory;
    }

}
