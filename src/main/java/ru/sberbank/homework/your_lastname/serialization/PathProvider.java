package ru.sberbank.homework.your_lastname.serialization;

import ru.sberbank.homework.common.CachePathProvider;

import java.io.File;

public class PathProvider implements CachePathProvider{
    private String directory;

    PathProvider(String directory) {
        this.directory = directory;
    }

    public String getCacheDirectoryPath() {
        File file = new File(directory);

        if (file.exists()) {
            return directory;
        }

        return null;
    }
}
