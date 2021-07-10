package com.skillw.rpglib.api.inventory;

import com.skillw.rpglib.RPGLib;
import com.skillw.rpglib.api.able.Keyable;
import com.skillw.rpglib.util.ColorUtils;
import com.skillw.rpglib.util.FileUtils;
import com.skillw.rpglib.util.ItemUtils;
import io.izzel.taboolib.util.item.Items;
import io.izzel.taboolib.util.item.inventory.MenuBuilder;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

/**
 * @author Glom_
 * @date 2020/11/1 9:55
 */
public class DisplayInventory implements Keyable<String> {
    private final String key;
    private final String title;
    private final String[] layout;
    private final ConfigurationSection section;

    public DisplayInventory(String key, String title, String[] layout, ConfigurationSection section) {
        this.key = key;
        this.layout = layout;
        this.section = section;
        this.title = title;
    }

    public DisplayInventory(ConfigurationSection section) {
        this.key = section.getName();
        this.layout = section.getStringList("layout").toArray(new String[0]);
        this.section = section;
        this.title = section.getString("title");
    }

    public String getTitle() {
        return title;
    }

    public String[] getLayout() {
        return layout;
    }

    public MenuBuilder build(){
        return build(null);
    }

    public static DisplayInventory load(ConfigurationSection section) {
        return new DisplayInventory(section);
    }
    public static List<DisplayInventory> loadMultiply(File file) {
        List<DisplayInventory> displayInventories = FileUtils.loadMultiply(file,DisplayInventory.class);
        displayInventories.forEach(DisplayInventory::register);
        return displayInventories;
    }
    public MenuBuilder build(Player player){
        MenuBuilder builder = MenuBuilder.builder()
                .title(ColorUtils.color(title))
                .items(layout)
                .rows(Math.min(layout.length,6));
        ConfigurationSection itemsSection = section.getConfigurationSection("items");
        for (String key:itemsSection.getKeys(false)) {
            if(key.length() > 1) continue;
            char c = key.charAt(0);
            ConfigurationSection itemSection = itemsSection.getConfigurationSection(key);
            ItemStack itemStack;
            if(player != null) itemStack = ItemUtils.papiItem(Items.loadItem(itemSection),player);
            else itemStack = Items.loadItem(itemSection);
            builder.put(c,itemStack);
        }

        return builder;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void register() {
        RPGLib.getDisplayInventoryManager().register(this);
    }

    public ConfigurationSection getSection() {
        return section;
    }
}
