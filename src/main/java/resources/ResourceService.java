package resources;

import sax.ReadFileSax;

public class ResourceService {
    private static ResourceService instance;
    private ResourceService() {
    }

    public static ResourceService instance() {
        if (instance == null) {
            instance = new ResourceService();
        }
        return instance;
    }

    public <T> T getResource(String path, Class<T> clazz) {
        return clazz.cast(ReadFileSax.readXML(path));
    }
}