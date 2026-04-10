package com.falchus.lib.storage;

import com.falchus.lib.storage.serializer.Serializer;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Storage {

    private final Serializer<?> serializer;
    private final File file;
    private final String defaultContent;

    public Storage(Serializer<?> serializer, Path folder, String fileName, String defaultContent) {
        this.serializer = serializer;
        this.defaultContent = defaultContent;

        try {
            Files.createDirectories(folder);
            file = folder.resolve(fileName).toFile();

            if (!file.exists() || file.length() == 0) {
                Files.writeString(file.toPath(), defaultContent);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void save(T value) {
        try {
            String content = ((Serializer<T>) serializer).serialize(value);
            Files.writeString(file.toPath(), content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T load() {
        try {
            if (!file.exists() || file.length() == 0) {
                return ((Serializer<T>) serializer).deserialize(defaultContent);
            }

            String content = Files.readString(file.toPath());
            if (content == null || content.isBlank()) {
                return ((Serializer<T>) serializer).deserialize(defaultContent);
            }
            return ((Serializer<T>) serializer).deserialize(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void delete() {
        try {
            Files.deleteIfExists(file.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
