package tk.peanut.hydrogen.scripting;


import com.darkmagician6.eventapi.EventTarget;
import tk.peanut.hydrogen.events.EventMotionUpdate;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.scripting.runtime.events.ScriptMotionUpdateEvent;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class ScriptModule extends Module {
    private ScriptEngine engine;

    ScriptModule(String name, String desc, int keyBind, Category category, int color) {
        super(name, desc, keyBind, category, color);
    }

    public void setScriptEngine(ScriptEngine scriptEngine) {
        engine = scriptEngine;
    }


    @EventTarget
    public void onRender2D(EventRender2D event) {
        if (!getState()) return;
        try {
            ((Invocable) engine).invokeFunction("onRender2D");
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException ignored) {
        }
    }

    @EventTarget
    public void onMotionUpdate(EventMotionUpdate event) {
        if (!getState()) return;
        ScriptMotionUpdateEvent ev = new ScriptMotionUpdateEvent(event.getEventType(), event.getX(), event.getY(), event.getZ(), event.getYaw(), event.getPitch(), event.isOnGround());

        try {
            ((Invocable) engine).invokeFunction("onMotionUpdate", ev);
        } catch (NoSuchMethodException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        ev.apply(event);
    }
}