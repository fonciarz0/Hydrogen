package tk.peanut.hydrogen.ui.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.utils.ParticleGenerator;
import tk.peanut.hydrogen.utils.Utils;

/**
 * Created by peanut on 26/02/2021
 */
public class MainMenu extends GuiScreen {

    public static Minecraft mc = Minecraft.getMinecraft();
    public static ParticleGenerator particleGenerator = new ParticleGenerator(60, mc.displayWidth, mc.displayHeight);

    public static void drawMenu(int mouseX, int mouseY) {
        drawRect(40, 0, 140, Utils.getScaledRes().getScaledHeight(), 0x60000000);

        String mds = String.format("%s mods loaded, %s mods active", Loader.instance().getModList().size(), Loader.instance().getActiveModList().size());
        String fml = String.format("Powered by Forge %s", ForgeVersion.getVersion());
        String mcp = "MCP 9.19";
        String mcv = "Minecraft 1.8.9";
        String name = String.format("%s %s", Hydrogen.name, Hydrogen.version);
        String mname = String.format("Logged in as §7%s", Minecraft.getMinecraft().getSession().getUsername());

        mc.fontRendererObj.drawStringWithShadow(mds, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(mds) - 4, Utils.getScaledRes().getScaledHeight() - 14, -1);
        mc.fontRendererObj.drawStringWithShadow(fml, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(fml) - 4, Utils.getScaledRes().getScaledHeight() - 26, -1);
        mc.fontRendererObj.drawStringWithShadow(mcp, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(mcp) - 4, Utils.getScaledRes().getScaledHeight() - 38, -1);
        mc.fontRendererObj.drawStringWithShadow(mcv, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(mcv) - 4, Utils.getScaledRes().getScaledHeight() - 50, -1);

        mc.fontRendererObj.drawStringWithShadow(name, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(name) - 4, 4, -1);
        mc.fontRendererObj.drawStringWithShadow("Developed by §7zPeanut §fand §7UltramoxX", Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth("Developed by §7zPeanut §fand §7UltramoxX") - 4, 16, -1);
        mc.fontRendererObj.drawStringWithShadow(mname, Utils.getScaledRes().getScaledWidth() - mc.fontRendererObj.getStringWidth(mname) - 4, 28, -1);

        if(Hydrogen.getClient().outdated) {
            mc.fontRendererObj.drawStringWithShadow("§cOutdated!", 66, 10, -1);
            mc.fontRendererObj.drawStringWithShadow("Newest Version: §a" + Hydrogen.getClient().newversion, 42, 22, -1);
        }

        float scale = 5F;

        GL11.glScalef(scale, scale, scale);
        mc.fontRendererObj.drawStringWithShadow("Hydrogen", Utils.getScaledRes().getScaledWidth() / 2 / scale - 13, Utils.getScaledRes().getScaledHeight() / 2 / scale - 5F, -4198401);
        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);

        float scalever = 2.0F;

        GL11.glScalef(scalever, scalever, scalever);
        mc.fontRendererObj.drawStringWithShadow("§7" + Hydrogen.version, Utils.getScaledRes().getScaledWidth() / 2 / scalever + 85, Utils.getScaledRes().getScaledHeight() / 2 / scalever - 17F, -1);
        GL11.glScalef(1.0F / scalever, 1.0F / scalever, 1.0F / scalever);

        particleGenerator.drawParticles(mouseX, mouseY);
    }

}