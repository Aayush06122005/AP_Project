# AngryBirds

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.



## Links For Assets
- Cross button  : https://cdn-icons-png.flaticon.com/128/9604/9604673.png
- Play Button : Drawn on Canva
- Settings Button : Drawn on Canva
- Exit Button : Drawn on Canva
- Levels buttons : Drawn on Canva

- new materials : [https://th.bing.com/th/id/R.83722c1c7c541b0baafd30f5920dc4b6?rik=fu7IqyfqCDyU7g&riu=http%3A%2F%2Fimages.wikia.com%2Fangrybirdsfanon%2Fimages%2F4%2F45%2FAngry_Birds_Blocks.png&ehk=3aUmUa8gbxq3hktja2U5s1HXjvZTiwfAY12cTyPI6ig%3D&risl=&pid=ImgRaw&r=0](https://th.bing.com/th/id/R.83722c1c7c541b0baafd30f5920dc4b6?rik=fu7IqyfqCDyU7g&riu=http%3a%2f%2fimages.wikia.com%2fangrybirdsfanon%2fimages%2f4%2f45%2fAngry_Birds_Blocks.png&ehk=3aUmUa8gbxq3hktja2U5s1HXjvZTiwfAY12cTyPI6ig%3d&risl=&pid=ImgRaw&r=0)
- space : https://wallpapercave.com/wp/wp8124341.jpg
- ground : [https://th.bing.com/th/id/R.0cdf8d0087770f52fd7b7a4ac8ddce0a?rik=cueDaoH0bqOiww&riu=http%3A%2F%2Fnoobtuts.com%2Fcontent%2Funity%2F2d-angry-birds-game%2Fground.png&ehk=k1hmy0NoUroSOlOe6x1M4kN6Gea1s94n4alGpC1rL2g%3D&risl=&pid=ImgRaw&r=0](https://th.bing.com/th/id/R.0cdf8d0087770f52fd7b7a4ac8ddce0a?rik=cueDaoH0bqOiww&riu=http%3a%2f%2fnoobtuts.com%2fcontent%2funity%2f2d-angry-birds-game%2fground.png&ehk=k1hmy0NoUroSOlOe6x1M4kN6Gea1s94n4alGpC1rL2g%3d&risl=&pid=ImgRaw&r=0)

All other materials are drawn from canva.

### Flow of the game :

1. loading screen: tap anywhere on this screen to continue

2. mainmenu screen : three options are there : Play , Settings, Exit and option to See profile is also there.

3. AllLevel Screen : when we click on Play it directs to this screen where all the levels all shown.

4. GameScreen: whenwe click on an unlocked level it directs to this screen ehich is the main game.

5. ProfilePage: when we click on Angrybird logo in the main menu it directs to the this screen where the player name with account id and level is shown.

6. PauseScreen: when we click on pause button in the gamescreen , it directs to this screen , where we can resume exit or save the game.
7. win screen : when we click on win button it directs to this screen to show that player has won the game.
8. Lose screen : when we click on win button it directs to this screen to show that player has lost the game.
"# AP_Project" 
