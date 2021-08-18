# Bukkit-Plugin-Template
A Mineccraft Bukkit Plugin Template

# Usage
- This template is tailored of usage in VSCode. You should be prompted to install some VSCode extensions when you open the repo in VSCode to facilitate Java development. This template will still work in other IDEs and you can delete the `.vscode` folder if you don't plan to use VSCode.
- Install Java and Maven if you don't already have them installed. Google for tutorials on how to do this if you're not sure how.
- `pom.xml`
  - If not PCN, update `<groupId>`. Ex `me.parsonswy`
  - Update `<artifactId>`.
  - If using a different versioning notiation, update `<version>`
  - Update `<description>`
-  Project Structure
  - If not PCN update package structure to match your `<groudId>` Ex `me\parsonswy\templateus`
  - Change the `net.peacefulcraft.templateus` package to `[whatever you just set the package path to].[name of your plugin]` Ex `me.parsonswy.guishop`
  - Rename `Templateus.java` to match the name of your plugin. Ex `GuiShop.java`
  - Change `main\resources\plugin.yml` `main: net.peacefulcraft.templateus.Templateus` to match the path you just set above. Ex `me.parsonswy.guishop.GuiShop`. Note that the `.java` is left off here.
  - Moving / renaming in the above setups may result in compile errors in the `import` statements. Update any broken package paths accordingly.
  - Either remove of re-purpose the example command and permission in `plugin.yml`. See the Wiki article linked in `plugin.yml` for more details on what goes into `plugin.yml`.

# Compiling
- Open your OS' command terminal and navigate to this project's folder ( folder with `src`, `pom.xml`, `README.md`, etc ).
- Type `mvn package`
- Once the command is complete, there will be a jar file in `target\[<artifactId>]-[version].jar`

  For questions, comments, or suggestions on this repo or Bukkit plugin development in general:
  - https://spigotmc.org
  - https://bukkit.org
  - https://www.peacefulcraft.net/flarum/t/github