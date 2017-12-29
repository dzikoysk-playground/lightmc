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

package net.dzikoysk.lightmc.core.command;

import net.dzikoysk.lightmc.Light;

import java.util.HashMap;
import java.util.Map;

public class BukkitCommandCenter {

    private final Light light;
    private final Map<String, BukkitCommand> commands;

    public BukkitCommandCenter(Light light) {
        this.light = light;
        this.commands = new HashMap<>();
    }

    public void registerCommand(BukkitCommand bukkitCommand) {
        commands.put(bukkitCommand.getCommand(), bukkitCommand);
    }

    public Map<String, BukkitCommand> getCommands() {
        return commands;
    }

    public Light getLight() {
        return light;
    }

}
