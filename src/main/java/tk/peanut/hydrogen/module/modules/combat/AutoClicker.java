package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.TimeHelper;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "AutoClicker", description = "Automatically clicks for you", category = Category.Combat)
public class AutoClicker extends Module {

    Random random = new Random();
    TimeHelper time = new TimeHelper();
    int delay;

    public AutoClicker() {
        super(Keyboard.KEY_NONE);

        ArrayList<String> mode = new ArrayList<>();
        mode.add("Left Click");
        mode.add("Right Click");
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Type", this, "Left Click", mode));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("CPS", this, 9, 1, 20, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("on Click", this, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Random ms", this, 5, 0, 250, true));
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        boolean type = Hydrogen.getClient().settingsManager.getSettingByName(this, "Type").getValString().equalsIgnoreCase("Left Click");

        if (this.time.isDelayComplete(delay)) {
            if (Hydrogen.getClient().settingsManager.getSettingByName("on Click").isEnabled()) {
                if(type) {
                    if (Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed) {
                        this.click();
                    }
                } else {
                    if (Minecraft.getMinecraft().gameSettings.keyBindUseItem.pressed) {
                        this.click();
                    }
                }
            } else {
                this.click();
            }
        }
    }

    private void click() {
        boolean type = Hydrogen.getClient().settingsManager.getSettingByName(this, "Type").getValString().equalsIgnoreCase("Left Click");
        delay = (int) Math.round(1000 / Hydrogen.getClient().settingsManager.getSettingByName(this, "CPS").getValDouble());
        int random = (int) (Math.random() * Hydrogen.getClient().settingsManager.getSettingByName(this, "Random ms").getValDouble());
        delay = delay + random;
        this.time.setLastMS();
        if(type) {
            mc.clickMouse();
        } else {
            mc.rightClickMouse();
        }
        mc.playerController.attackEntity(mc.thePlayer, mc.objectMouseOver.entityHit);
    }
}
