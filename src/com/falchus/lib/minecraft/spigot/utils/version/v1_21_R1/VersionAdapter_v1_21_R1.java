package com.falchus.lib.minecraft.spigot.utils.version.v1_21_R1;

import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapterModern;

public class VersionAdapter_v1_21_R1 extends VersionAdapterModern {

    @Override
    public double[] getRecentTps() {
        try {
            Object server = getMcServer();
            Object value = minecraftServer_recentTps.get(server);
            
            // tickTimes stores nanoseconds per tick; convert to TPS approximation when needed
            if (value instanceof long[] times) {
                double[] tps = new double[times.length];
                for (int i = 0; i < times.length; i++) {
                    double mspt = times[i] / 1_000_000.0;
                    tps[i] = mspt == 0 ? 20.0 : Math.min(20.0, 1000.0 / mspt);
                }
                return tps;
            }
            return (double[]) value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}