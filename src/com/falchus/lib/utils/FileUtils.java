package com.falchus.lib.utils;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtils {
	
	public static void createFolder(Path path, FileAttribute<?>... attributes) {
		try {
			Files.createDirectories(path, attributes);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void writeString(Path path, CharSequence content, OpenOption... options) {
		try {
			Files.writeString(path, content, options);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void writeString(Path path, CharSequence content) {
		writeString(path, content,
			StandardOpenOption.CREATE,
			StandardOpenOption.TRUNCATE_EXISTING,
			StandardOpenOption.WRITE
		);
	}
	
	public static String readString(Path path) {
		try {
			return Files.readString(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void copy(Path source, Path target, CopyOption... options) {
		try {
			Files.copy(source, target, options);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void copy(Path source, Path target) {
		copy(source, target,
			StandardCopyOption.REPLACE_EXISTING
		);
	}
	
	public static void copyFolder(Path source, Path target, Set<FileVisitOption> fileVisitOptions, Set<CopyOption> copyOptions) {
		try (Stream<Path> stream = Files.walk(source, fileVisitOptions.toArray(new FileVisitOption[0]))) {
			stream.forEach(path -> {
				Path destination = target.resolve(source.relativize(path));
				if (Files.isDirectory(path)) {
					createFolder(destination);
				} else {
					copy(path, destination, copyOptions.toArray(new CopyOption[0]));
				}
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void copyFolder(Path source, Path target, FileVisitOption... fileVisitOptions) {
		copyFolder(source, target, Set.of(fileVisitOptions), Set.of(
			StandardCopyOption.REPLACE_EXISTING
		));
	}
	
	public static void copyFolder(Path source, Path target, CopyOption... copyOptions) {
		copyFolder(source, target, Set.of(), Set.of(copyOptions));
	}
	
	public static void copyFolder(Path source, Path target) {
		copyFolder(source, target,
			StandardCopyOption.REPLACE_EXISTING
		);
	}
	
	public static void delete(Path path) {
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void deleteFolder(Path path) {
		try (Stream<Path> stream = Files.walk(path)) {
			stream.sorted(Comparator.reverseOrder())
				.forEach(FileUtils::delete);
		} catch (NoSuchFileException ignored) {
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
