package me.peanut.hydrogen.module.modules.gui;

import me.peanut.hydrogen.module.Category;
import me.peanut.hydrogen.module.Info;
import me.peanut.hydrogen.module.Module;
import me.peanut.hydrogen.Hydrogen;
import me.peanut.hydrogen.settings.Setting;

/**
 * Created by peanut on 26/02/2021
 */
@Info(name = "MainMenu", category = Category.Gui, description = "Enables the custom main menu")
public class MainMenuModule extends Module {
    public MainMenuModule() {
        h2.settingsManager.rSetting(new Setting("Rainbow", this, true));
        h2.settingsManager.rSetting(new Setting("Startup Sound", this, true));
    }
}
