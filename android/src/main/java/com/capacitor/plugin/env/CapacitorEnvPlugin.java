package com.capacitor.plugin.env;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.util.Iterator;
import org.json.JSONObject;

@CapacitorPlugin(name = "CapacitorEnv")
public class CapacitorEnvPlugin extends Plugin {

    private CapacitorEnv implementation = new CapacitorEnv();

    // @PluginMethod
    // public void echo(PluginCall call) {
    //     String value = call.getString("value");

    //     JSObject ret = new JSObject();
    //     ret.put("value", implementation.echo(value));
    //     call.resolve(ret);
    // }

    @PluginMethod
    public void get(PluginCall call) {
        try {
            JSObject result = new JSObject();

            JSONObject json = getConfig().getConfigJSON();
            Iterator<String> iter = json.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                result.put(key, json.getString(key));
            }

            call.resolve(result);
        } catch (Exception e) {
            call.reject("Error reading CapacitorEnv values: " + e.getMessage());
        }
    }
}
