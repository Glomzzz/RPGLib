package com.skillw.rpglib.command;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.manager.script.CompiledScriptManager;
import com.skillw.rpglib.script.ScriptTool;
import com.skillw.rpglib.util.MessageUtils;
import io.izzel.taboolib.module.command.base.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@BaseCommand(name = "rpglib", aliases = "rb", description = "主指令", permission = "rl.command")
public class RPGLibCommand extends BaseMainCommand {
    @SubCommand(
            permission = "rl.command.js",
            arguments = {"脚本路径"},
            description = "执行脚本指令"
    )
    BaseSubCommand js = new BaseSubCommand() {
        @Override
        public void onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
            if (args.length < 1) return;
            String fileName = args[0];
            String[] argsNew = new String[args.length-1];
            for (int i = 1; i < args.length; i++) {
                argsNew[i-1] = args[i];
            }
            Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
            concurrentHashMap.put("sender",sender);
            concurrentHashMap.put("args",argsNew);
            MessageUtils.sendConsole("&a正在执行脚本 &6" + fileName + "&a!");
            ScriptTool.invokePathWithFunction(fileName,concurrentHashMap);
        }

        @Override
        public String getLabel() {
            return "js";
        }

        @Override
        public Argument[] getArguments() {
            return new Argument[]{new Argument("脚本路径", true, CompiledScriptManager::getFilePaths)};
        }
    };

    @SubCommand(
            permission = "rl.command.reload",
            description = "重载指令"
    )
    BaseSubCommand reload = new BaseSubCommand() {
        @Override
        public void onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
            RPGLib.getConfigManager().reload();
            MessageUtils.sendConsole("&aRPGLib重载成功!");
        }

        @Override
        public String getLabel() {
            return "reload";
        }

    };
}
