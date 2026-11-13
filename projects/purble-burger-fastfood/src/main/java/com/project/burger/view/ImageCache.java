package com.project.burger.view;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/** Share decoded images, never ImageView nodes (each node can have only one parent). */
final class ImageCache {
    private static final Map<String, Image> IMAGES = new ConcurrentHashMap<>();

    private ImageCache() {
    }

    static Image get(String resourcePath) {
        return IMAGES.computeIfAbsent(resourcePath, path -> {
            URL resource = ImageCache.class.getResource(path);
            if (resource == null) {
                throw new IllegalArgumentException("Missing image resource: " + path);
            }
            Image image = new Image(resource.toExternalForm());
            if (image.isError()) {
                throw new IllegalStateException("Cannot decode image: " + path, image.getException());
            }
            return image;
        });
    }

    static Optional<Image> find(String resourcePath) {
        return ImageCache.class.getResource(resourcePath) == null
                ? Optional.empty() : Optional.of(get(resourcePath));
    }
}
