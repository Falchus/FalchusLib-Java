package com.falchus.lib.minecraft.spigot.packets.wrapper;

import com.falchus.lib.minecraft.spigot.packets.wrapper.chat.WrappedPacketInChat;
import com.falchus.lib.minecraft.spigot.packets.wrapper.chat.WrappedPacketOutChat;
import com.falchus.lib.minecraft.spigot.utils.version.IVersionAdapter;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.Dummy;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class PacketWrapper extends FirstClassWrapper<Object> {

    protected static final IVersionAdapter version = VersionProvider.get();
    private static final String networkProtocol = version.getPackageNm() + "network.protocol.";
    protected static final String networkProtocolCommon = networkProtocol + "common.";
    protected static final String networkProtocolGame = networkProtocol + "game.";

    private static final Map<Class<?>, Function<Object, PacketWrapper>> registry = new HashMap<>();

    public PacketWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);
    }

    @SuppressWarnings("unchecked")
    private static <T extends PacketWrapper> void register() {
        if (!registry.isEmpty()) return;
        Class<T>[] wrappers = new Class[]{
                WrappedPacketInChat.class,
                WrappedPacketOutChat.class
        };

        for (Class<T> wrapper : wrappers) {
            try {
                PacketWrapper dummy = (T) new ClassInstanceBuilder(
                        wrapper
                ).withParams(
                        Map.of(
                                Object.class,
                                Dummy.instance
                        )
                ).build();
                for (Class<?> clazz : dummy.getClasses()) {
                    registry.put(clazz, obj -> {
                        try {
                            return (T) new ClassInstanceBuilder(
                                    wrapper
                            ).withParams(
                                    Map.of(
                                            Object.class,
                                            obj
                                    )
                            ).build();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static PacketWrapper wrap(Object packet) {
        register();

        Function<Object, PacketWrapper> wrapper = registry.get(packet.getClass());
        if (wrapper != null) {
            return wrapper.apply(packet);
        }
        return new PacketWrapper(packet, Set.of(packet.getClass().getName()));
    }

    public <T extends PacketWrapper> T as(@NonNull Class<T> clazz) {
        return as(clazz, Object.class);
    }
}
