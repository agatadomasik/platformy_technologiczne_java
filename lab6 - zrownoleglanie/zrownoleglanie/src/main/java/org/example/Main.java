package org.example;

import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        String loadPath = "C:/Users/agata/Desktop/zrownoleglanie/images/orginal";
        String savePath = "C:/Users/agata/Desktop/zrownoleglanie/images/processed";
        Path source = Path.of(loadPath);
        try (Stream<Path> stream = Files.list(source)) {

            int poolSize = 1;
            ExecutorService executor = Executors.newFixedThreadPool(poolSize);
            long time = System.currentTimeMillis();

            //executor.submit(() -> {
                stream.parallel()
                        .map(file -> {
                            String name = file.getFileName().toString();
                            try {
                                BufferedImage originalImage = ImageIO.read(file.toFile());
                                return Pair.of(name, originalImage);
                            } catch (IOException e) {
                                System.err.println("Error reading an image: " + name);
                                throw new RuntimeException(e);
                            }
                        })
                        .map(pair -> {
                            String name = pair.getLeft();
                            BufferedImage originalImage = pair.getRight();
                            BufferedImage processedImage = processImage(originalImage);
                            return Pair.of(name, processedImage);
                        })
                        .forEach(pair -> executor.submit(() -> saveImage(pair.getRight(), savePath, pair.getLeft())));
            //});
            executor.shutdown();
            while(!executor.isTerminated()){
                // wait for tasks to finish
            }
            System.out.println("Execution time: " + (float) (System.currentTimeMillis() - time) / 1000 + " seconds");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage processImage(BufferedImage originalImage) {
        BufferedImage processedImage = new BufferedImage(originalImage.getWidth(),
                originalImage.getHeight(), originalImage.getType());
        for (int i = 0; i < originalImage.getWidth(); i++) {
            for (int j = 0; j < originalImage.getHeight(); j++) {
                int rgb = originalImage.getRGB(i, j);
                Color color = new Color(rgb);
                int red, green, blue;

                if(color.getRed()>127) red = Integer.min(color.getRed()+30,255);
                else red = Integer.max(color.getRed()-30,0);

                if(color.getBlue()>127) blue = Integer.min(color.getBlue()+30,255);
                else blue = Integer.max(color.getBlue()-30,0);

                if(color.getGreen()>127) green = Integer.min(color.getGreen()+30,255);
                else green = Integer.max(color.getGreen()-30,0);

                Color outColor = new Color(red, green, blue);
                int outRgb = outColor.getRGB();
                processedImage.setRGB(i, j, outRgb);
            }
        }
        return processedImage;
    }

    private static void saveImage(BufferedImage image, String savePath, String name) {
        Path path = Path.of(savePath + "/" + name);
        try {
            ImageIO.write(image, "jpg", path.toFile());
        } catch (IOException e) {
            System.err.println("Error saving the image: " + name);
            throw new RuntimeException(e);
        }
    }
}
