package de.knacrack.journeymc.utils.handler;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class YmlFileHandler {

    private Yaml yaml;

    public YmlFileHandler() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new Yaml(options);
    }

    @Deprecated
    public void writeToYmlFile(Object obj, Path path) throws IOException {
        String yml = yaml.dump(obj);
        Files.writeString(path, yml);
    }

    @Deprecated
    public <T> T readFromYmlFile(Path path, Class<T> clazz) throws IOException, YAMLException {
        String yml = Files.readString(path);
        return yaml.loadAs(yml, clazz);
    }

    public void writeObjectToYamlFile(Object obj, Path path) throws IOException {
        String yml = yaml.dump(obj);
        Files.writeString(path, yml);
    }

    public <T> Optional<T> readObjectFromYamlFile(Path path, Class<T> clazz) throws IOException, YAMLException {
        String yml = Files.readString(path);
        T obj = yaml.loadAs(yml, clazz);
        return Optional.ofNullable(obj);
    }
}

