```xml
<repository>
  <id>falchus.com</id>
  <url>https://repo.falchus.com/releases</url>
</repository>
```
```xml
<dependency>
  <groupId>com.falchus</groupId>
  <artifactId>lib-java</artifactId>
  <version>[1.0.0,)</version>
  <scope>provided</scope>
</dependency>
```

<details>
<summary>plugin.yml</summary>

```yaml
name: Example
version: 1.0.0
main: com.example.Example
author: Example

depend: [FalchusLib]
```
</details>
<details>
<summary>velocity-plugin.json</summary>

```json
{
  "id": "example",
  "name": "Example",
  "version": "1.0.0",
  "main": "com.example.Example",
  "authors": ["Example"],
  "dependencies": [{"id": "falchuslib", "optional": false}]
}
```
</details>

### Download
Get the latest release [**here**](https://www.spigotmc.org/resources/falchuslib.132261/).

![](https://bstats.org/signatures/bukkit/FalchusLib.svg)
![](https://bstats.org/signatures/velocity/FalchusLib.svg)
