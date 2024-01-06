package me.peanut.hydrogen.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

//
// Created by peanut on 06.01.2024
//
@IFMLLoadingPlugin.MCVersion(value = "1.8.9")
public class MixinLoader implements IFMLLoadingPlugin {

    public MixinLoader() {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.hydrogen.json");
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    @NotNull
    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Nullable
    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {

    }

    @Nullable
    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
