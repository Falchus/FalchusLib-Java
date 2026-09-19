package com.falchus.lib.storage;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.falchus.lib.storage.serializer.Serializer;
import com.falchus.lib.task.Task;
import com.falchus.lib.utils.FileUtils;

public class Storage {

	private final Serializer<?> serializer;
	private final Path folder;
	private final File file;
	private final String defaultContent;
	
	public Storage(Serializer<?> serializer, Path folder, String fileName, String defaultContent) {
		this.serializer = serializer;
		this.folder = folder;
		this.defaultContent = defaultContent;
		
		FileUtils.createFolder(folder);
		file = folder.resolve(fileName).toFile();
		
		if (!file.exists() || file.length() == 0) {
			write(defaultContent);
		}
	}
	
	public void write(String content) {
		FileUtils.writeString(file.toPath(), content);
	}
	
	@SuppressWarnings("unchecked")
	public <T> String serialize(T value) {
		return ((Serializer<T>) serializer).serialize(value);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T deserialize(String content) {
		return ((Serializer<T>) serializer).deserialize(content);
	}
	
	public <T> void save(T value) {
    	String content = serialize(value);
    	write(content);
	}
	
	public <T> void saveAsync(T value) {
		String content = serialize(value);
		Task.of(() -> write(content)).runAsync();
	}
	
	public <T> T load() {
		if (!file.exists() || file.length() == 0) {
			return deserialize(defaultContent);
		}
		
		String content = FileUtils.readString(file.toPath());
		if (content == null || content.isBlank()) {
			return deserialize(defaultContent);
		}
		
		try {
			return deserialize(content);
		} catch (Exception e) {
			write(defaultContent);
			return deserialize(defaultContent);
		}
	}
	
	public <T> void loadAsync(Consumer<T> consumer) {
		Task.of(() -> consumer.accept(load())).runAsync();
	}
	
	public void delete() {
		FileUtils.delete(file.toPath());
	}
	
	public void deleteFolder() {
		FileUtils.deleteFolder(folder);
	}
}
