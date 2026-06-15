package com.falchus.lib.storage;

import java.io.File;
import java.nio.file.Path;

import com.falchus.lib.storage.serializer.Serializer;
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
			FileUtils.writeString(file.toPath(), defaultContent);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> void save(T value) {
    	String content = ((Serializer<T>) serializer).serialize(value);
    	FileUtils.writeString(file.toPath(), content);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load() {
		if (!file.exists() || file.length() == 0) {
			return ((Serializer<T>) serializer).deserialize(defaultContent);
		}
		
		String content = FileUtils.readString(file.toPath());
		if (content == null || content.isBlank()) {
			return ((Serializer<T>) serializer).deserialize(defaultContent);
		}
		return ((Serializer<T>) serializer).deserialize(content);
	}
	
	public void delete() {
		FileUtils.delete(file.toPath());
	}
	
	public void deleteFolder() {
		FileUtils.deleteFolder(folder);
	}
}
