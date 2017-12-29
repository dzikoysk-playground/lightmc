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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BukkitCommandPerformer extends Command {

    private final BukkitCommand bukkitCommand;

    public BukkitCommandPerformer(String command, BukkitCommand bukkitCommand) {
        super(command);
        this.bukkitCommand = bukkitCommand;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return bukkitCommand != null && bukkitCommand.onCommand(sender, this, commandLabel, args);
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (bukkitCommand == null) {
            return null;
        }
        return bukkitCommand.onTabComplete(sender, this, alias, args);
    }

    public BukkitCommand getBukkitCommand() {
        return bukkitCommand;
    }

}
