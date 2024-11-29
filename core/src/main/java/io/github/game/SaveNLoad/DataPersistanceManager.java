package io.github.game.SaveNLoad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;
import java.util.List;

public class DataPersistanceManager {

    private static DataPersistanceManager instance;
    private String fileName;
    private GameData gameData;
    private List<IDataPersistance> dataPersistanceObjects;
    private FileDataHandler dataHandler;

    // Singleton access
    public static DataPersistanceManager getInstance() {
        if (instance == null) {
            instance = new DataPersistanceManager();
        }
        return instance;
    }

    // Private constructor to ensure singleton
    private DataPersistanceManager() {
        // Initialize the list to avoid NullPointerException
        dataPersistanceObjects = new ArrayList<>();
    }

    // Initialize the DataPersistenceManager with file name and data handler
    public void initialize(String fileNam) {
        fileName = fileNam;
        this.dataHandler = new FileDataHandler(Gdx.files.getLocalStoragePath(), fileName);
        startGame();
    }

    // Create new game data (initialize default values)
    public void newGame() {
        this.gameData = new GameData();
    }

    // Start the game and load data
    public void startGame() {
        loadGame();
    }

    // Save game data
    public void saveGame() {
        // Make sure we have valid data persistence objects before trying to save
        for (IDataPersistance dataPersistence : dataPersistanceObjects) {
            dataPersistence.Save(gameData);
        }
        dataHandler.saveData(gameData);
        Gdx.app.log("DataPersistenceManager", "Saved game data: " + gameData.score + " and " + gameData.levelUnlcoked);
    }

    // Load game data
    public void loadGame() {
        this.gameData = dataHandler.loadData();

        if (this.gameData == null) {
            Gdx.app.log("DataPersistenceManager", "No data found to load. Initializing to default values.");
            newGame();
        }

        for (IDataPersistance dataPersistence : dataPersistanceObjects) {
            dataPersistence.Load(gameData);
        }
    }

    // Save data when the application quits
    public void onApplicationQuit() {
        saveGame();
    }

    public void registerDataPersistenceObject(IDataPersistance dataPersistence) {
        if (!dataPersistanceObjects.contains(dataPersistence)) {
            dataPersistanceObjects.add(dataPersistence);
        }
    }

    // Unregister an object that implements IDataPersistence
    public void unregisterDataPersistanceObject(IDataPersistance dataPersistence) {
        dataPersistanceObjects.remove(dataPersistence);
    }

    // Find all IDataPersistence objects in the scene (via reflection)
    private List<IDataPersistance> findAllDataPersistanceObjects() {
        return new ArrayList<>(dataPersistanceObjects);
    }
}
