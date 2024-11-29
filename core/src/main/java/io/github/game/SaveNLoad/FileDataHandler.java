package io.github.game.SaveNLoad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import io.github.game.SaveNLoad.GameData;

public class FileDataHandler {

    private String relativePath;
    private String fileName;

    public FileDataHandler(String relativePath, String fileName) {
        this.relativePath = relativePath;
        this.fileName = fileName;
    }

    // Load game data from the specified file
    public GameData loadData() {
        String fullPath = relativePath + "/" + fileName; // Combine path and file name
        GameData loadedData = null;

        FileHandle file = Gdx.files.local(fullPath);

        if (file.exists()) {
            try {
                // Read the file content
                String dataToLoad = file.readString();

                // Use LibGDX's Json to deserialize the JSON data into a GameData object
                Json json = new Json();
                loadedData = json.fromJson(GameData.class, dataToLoad);
            } catch (Exception e) {
                // Log error if reading the file fails
                Gdx.app.error("FileDataHandler", "Error occurred during loading the file: " + fullPath + "\n" + e);
            }
        }
        return loadedData;
    }

    // Save game data to the specified file
    public void saveData(GameData data) {
        String fullPath = relativePath + "/" + fileName; // Combine path and file name

        try {
            FileHandle file = Gdx.files.local(fullPath);

            // If the file exists, delete it before creating a new one (to overwrite)
            if (file.exists()) {
                Gdx.app.log("FileDataHandler", "Writing the existing save file");
                file.delete(); // Delete the existing file
            } else {
                Gdx.app.log("FileDataHandler", "No save file found. Creating a new one.");
            }

            // Write the GameData to the file
            String dataToSave = new Json().toJson(data);
            file.writeString(dataToSave, false); // 'false' means overwrite if the file exists

        } catch (Exception e) {
            // Log error if saving the file fails
            Gdx.app.error("FileDataHandler", "Error occurred during saving the file: " + fullPath + "\n" + e);
        }
    }
}
