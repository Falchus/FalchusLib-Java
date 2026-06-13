# FalchusLib

A library designed to simplify & speed up software development.

### Features
- Reusable utilities for common tasks
- Abstraction layers for easier integration
- Productivity helpers to speed up development
- Cross-platform and maintainable design

### Installation
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
</dependency>
```

### Usage
#### Tasks
`com.falchus.lib.task.Task`
```java
void run();
static Task run(Runnable runnable);

static Task runTimer(Runnable runnable, long period, TimeUnit unit);
void runTimer(long period, TimeUnit unit);

static Task runTimer(Runnable runnable, long delay, long period, TimeUnit unit);
void runTimer(long delay, long period, TimeUnit unit);

static Task runLater(Runnable runnable, long delay, TimeUnit unit);
void runLater(long delay, TimeUnit unit);

static void end(int id);
void end();

int getId();

@Override
void onRun(int tick) {}

@Override
void onEnd() {}
```
- `com.falchus.lib.task.impl.CountdownTask`
  ```java
  CountdownTask(int remaining);
  
  @Override
  void onCountdown(int remaining) {}
  ```

#### Builders
`com.falchus.lib.utils.builder.ClassInstanceBuilder`
```java
ClassInstanceBuilder(Class clazz);

ClassInstanceBuilder(String name);
ClassInstanceBuilder(String... names);

ClassInstanceBuilder withParams(Map<Class, Object>... params);

Object build();
```

`com.falchus.lib.utils.builder.HTTPServerBuilder`
```java
HTTPServerBuilder port(int port);

HTTPServerBuilder route(String path, BiConsumer<HttpExchange, Map<String, String>> handler);
HTTPServerBuilder defaultHandler(BiConsumer<HttpExchange, Map<String, String>> handler);

HTTPServer build(String ip);
```

#### Storage
`com.falchus.lib.storage.Storage`
```java
<T> void save(T value);

<T> T load();

void delete();
void deleteFolder();
```
- `com.falchus.lib.storage.impl.json.JsonStorage`
  - `com.falchus.lib.storage.impl.json.JsonArrayStorage`
  - `com.falchus.lib.storage.impl.json.JsonObjectStorage`

`com.falchus.lib.storage.serializer.Serializer`
```java
String serialize(T value);
T deserialize(String content);
```
- `com.falchus.lib.storage.serializer.json.JsonSerializer`
  - `com.falchus.lib.storage.serializer.json.JsonArraySerializer`
  - `com.falchus.lib.storage.serializer.json.JsonObjectSerializer`

#### HTTP
`com.falchus.lib.utils.http.HTTPRequest`
```java
static String get(String url, Map<String, String> headers);
static String get(String url);

static String post(String url, Map<String, String> headers, String body);
static String post(String url, String body);

static String put(String url, Map<String, String> headers, String body);
static String put(String url, String body);

static String delete(String url, Map<String, String> headers);
static String delete(String url);

static String head(String url, Map<String, String> headers);
static String head(String url);

static String request(String method, String url, Map<String, String> headers, String body);
```

`com.falchus.lib.utils.http.HTTPServer`
```java
void start();
void stop(int delay);

static void sendText(HttpExchange exchange, String text, int statusCode);
static void sendJson(HttpExchange exchange, String json, int statusCode);

static void redirect(HttpExchange exchange, String location, int statusCode);
```

#### Events
`com.falchus.lib.events.Cancellable`
```java
boolean isCancelled();
void setCancelled(boolean cancelled);
```

`com.falchus.lib.events.Event`
```java
Event(boolean async);

boolean callEvent();
```
`com.falchus.lib.events.EventExecutor`
```java
void execute(Event event);
```
`com.falchus.lib.events.EventHandler`
```java
EventPriority priority();

boolean ignoreCancelled();
```
`com.falchus.lib.manager.EventManager`
```java
static void registerListener(Listener listener);
static void unregisterListener(Listener listener);

static void callEvent(Event event);
```

`com.falchus.lib.events.listener.Listener`
`com.falchus.lib.events.listener.RegisteredListener`
```java
void execute(Event event);
```

#### Reflection & Wrapping
`com.falchus.lib.utils.reflection.ReflectionUtils`
```java
static Class getClass(String name);
static Class getFirstClass(String... names);

static Field getField(Class clazz, String name);
static Field getField(Object instance, String name);
static Field getFirstField(Class clazz, String... names);
static Field getFirstField(Object instance, String... names);
static Field getFirstField(Set<Class> classes, String... names);

static <T> T getFieldValue(Object instance, Field field);
static <T> T getFieldValue(Field field);
static <T> T getFieldValue(Object instance, Field field, Class<T> type);
static <T> T getFieldValue(Field field, Class<T> type);

static void setField(Object instance, Field field, Object value);
static void setField(Object instance, String name, Object value);
static void setField(Field field, Object value);
static void setField(Class clazz, String name, Object value);
static void setFirstField(Object instance, Object value, String... names);
static void setFirstField(Class clazz, Object value, String... names);
static void setFirstField(Object instance, Set<Class> classes, Object value, String... names);

static Method getMethod(Class clazz, String name, Class... params);
static Method getMethod(Object instance, String name, Class... params);
static Method getFirstMethod(Class clazz, List<Class> params, String... names);
static Method getFirstMethod(Object instance, List<Class> params, String... names);
static Method getFirstMethod(Set<Class> classes, List<Class> params, String... names);

static Constructor getConstructor(Class clazz, Class... params);
static Constructor getConstructor(Object instance, Class... params);
static Constructor getFirstConstructor(Class clazz, Set<List<Class>> params);
static Constructor getFirstConstructor(Object instance, Set<List<Class>> params);
static Constructor getFirstConstructor(Set<Class> classes, Set<List<Class>> params);
```

`com.falchus.lib.utils.wrapper.Wrapper`
```java
Wrapper(T handle);

Field getField(String name);
Field getFirstField(String... names);

<U> U getFieldValue(Field field);
<U> U getFieldValue(Field field, Class<U> type);

void setField(Field field, Object value);
void setFirstField(Object value, String... names);

Method getMethod(String name, Class... params);
Method getFirstMethod(List<Class> params, String... names);

Constructor getConstructor(Class... params);
Constructor getFirstConstructor(Set<List<Class>> params);

<U extends Wrapper<?>> U as(Class<U> clazz, Class... params);
```
- `com.falchus.lib.utils.wrapper.impl.ClassWrapper`
  ```java
  ClassWrapper(T handle, Set<Class> classes);
  
  Set<Class> getClasses();
  ```
  - `com.falchus.lib.utils.wrapper.impl.FirstClassWrapper`
    ```java
    FirstClassWrapper(T handle, Set<String> names);
    ```

#### Utils
`com.falchus.lib.utils.JsonUtils`
```java
static JsonElement get(String json, String path);

static String getString(String json, String path);

static long getLong(String json, String path);

static boolean getBoolean(String json, String path);
```

`com.falchus.lib.utils.JsoupUtils`
```java
static String getScriptDataById(String html, String id);
```

`com.falchus.lib.utils.MathUtils`
```java
static float angleDiff(float angle1, float angle2);

static float gcd(float current, float previous);

static <T extends Number> T getMode(Collection<T> collection);
```

`com.falchus.lib.utils.StringUtils`
```java
static <T extends Collection<? super String>> T copyPartialMatches(String token, Iterable<String> originals, T collection);

static boolean startsWithIgnoreCase(String string, String prefix);
```

<details>
<summary>Minecraft</summary>

*Use `<scope>provided</scope>` and install via /plugins folder!*

*Download: https://www.spigotmc.org/resources/falchuslib.132261*

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

#### Usage
##### Utils
`com.falchus.lib.minecraft.spigot.utils.BlockUtils`
```java
static boolean isLiquid(Block block);

static boolean isSlab(Block block);

static boolean isStair(Block block);

static boolean isClimbable(Block block);

static boolean isSoftLanding(Block block);
```
`com.falchus.lib.minecraft.spigot.utils.EntityUtils`
```java
static Object getEntity(Entity entity);

static Entity getBukkitEntity(Object entity);

static Entity getEntityById(World world, int id);

static WrappedAxisAlignedBB getBoundingBox(Entity entity);
static WrappedAxisAlignedBB modifyBoundingBox(WrappedAxisAlignedBB axisAlignedBB, double minX, double minY, double minZ, double maxX, double maxY, double maxZ);

static boolean isOnGround(Entity entity, double yExpand);
static boolean isOnGround(Entity entity);

static boolean isInLiquid(Entity entity);

static boolean isOnSlime(Entity entity);

static boolean isOnStairs(Entity entity);

static boolean isOnIce(Entity entity);

static boolean isOnClimbable(Entity entity);

static boolean isUnderBlock(Entity entity);

static double getAbsorption(Damageable entity);
static void setAbsorption(Damageable entity, double absorption);

static void setYawPitch(Object entity, float yaw, float pitch);
```

`com.falchus.lib.minecraft.spigot.utils.ItemUtils`
```java
static ItemStack setUUID(ItemStack item, UUID uuid);
static UUID getUUID(ItemStack item);

static ItemStack clearNBT(ItemStack item);

static ItemStack[] itemStackArrayFromBase64(String base64);
static String itemStackArrayToBase64(ItemStack[] items);
```

`com.falchus.lib.minecraft.spigot.utils.PlayerUtils`
```java
static void sendPacket(Player player, Object packet);

static void sendTitle(Player player, String title, String subtitle);

static void playSound(Player player, Location location, Sound sound, float volume, float pitch);

static void freeze(Player player);
static void unfreeze(Player player);

static String getLuckPermsRankPrefix(Player player);

static void vanish(Player player);
static void unvanish(Player player);

static Object getEntityPlayer(Player player);

static GameProfile getProfile(Object entityPlayer);

static int getPing(Player player);

static int getPotionEffectLevel(Player player, PotionEffectType type);

static void setSkin(Player player, UUID uuid);
static void resetSkin(Player player);

static void setName(Player player, String name);
static void resetName(Player player);

static void refresh(Player player);

static void addEntityPlayer(Player player, Object entityPlayer);
static void removeEntityPlayer(Player player, Object entityPlayer);
static void spawnEntityPlayer(Player player, Object entityPlayer);

static void connectToServer(Player player, String server);

static boolean isHoldingSword(Player player);
```

`com.falchus.lib.minecraft.spigot.utils.SchedulerUtils`
```java
static void runTask(Runnable runnable);

static void runTaskAsync(Runnable runnable);

static void runTaskForEntity(Entity entity, Runnable runnable);

static void runTaskLater(Runnable runnable, long delayTicks);
```

`com.falchus.lib.minecraft.spigot.utils.ServerUtils`
```java
static Object getMcServer();
static Object getBukkitServer();

static Version getVersion();
static String getVersionString();
static int getMajorVersion();
static int getMinorVersion();

static double[] getRecentTps();
```

`com.falchus.lib.minecraft.spigot.utils.WorldUtils`
```java
static void setGameRule(World world, com.falchus.lib.minecraft.spigot.enums.GameRule gameRule, String value);

static Object[] getBiomes(World world);
static int getBiomeId(Biome biome);
static Object getNmsBiome(Biome biome);

static Object getWorldServer(World world);

static List<WrappedAxisAlignedBB> getCollidingBlocks(World world, WrappedAxisAlignedBB axisAlignedBB);

static Biome getBiome(com.falchus.lib.minecraft.spigot.enums.Biome biome);

static Material getMaterial(com.falchus.lib.minecraft.spigot.enums.Material material);
```

##### Commands
`com.falchus.lib.minecraft.command.BaseCommand`
```java
String getPermission();
String getNoPermissionMessage();

String getUsageMessage();

void executeCommand(Object sender, String[] args);

List<String> tabComplete(Object sender, String[] args);

boolean hasPermission(Object sender);

void sendMessage(Object s, String message);
```
- `com.falchus.lib.minecraft.command.impl.SpigotCommandAdapter`
- `com.falchus.lib.minecraft.command.impl.VelocityCommandAdapter`

##### Events
`com.falchus.lib.minecraft.spigot.events.LobbyCancelEvent`
```java
boolean isCancelled();

void setCancelled(boolean cancelled);

Event getEvent();
```

`com.falchus.lib.minecraft.spigot.events.player.PlayerClientJoinEvent`
```java
Player getPlayer();

Client getClient();
```

`com.falchus.lib.minecraft.spigot.events.player.PlayerPacketEvent`
```java
boolean isAsynchronous();

boolean isCancelled();

void setCancelled(boolean cancelled);

Player getPlayer();

PacketWrapper getPacket();
```
- `com.falchus.lib.minecraft.spigot.events.player.PlayerPacketInEvent`
- `com.falchus.lib.minecraft.spigot.events.player.PlayerPacketOutEvent`

##### Player Elements
`com.falchus.lib.minecraft.spigot.player.elements.PlayerElement`
```java
void update();
static <T extends PlayerElement> void updateAll(Class<T> clazz);

void sendUpdating(long intervalTicks, Runnable runnable);

void remove();

static <T extends PlayerElement> T get(Class<T> clazz, Player player);
```
- `com.falchus.lib.minecraft.spigot.player.elements.impl.Actionbar`
  ```java
  void send(BiFunction<Integer, Player, String> message);
  void send(Supplier<String> message);
  void sendUpdating(long intervalTicks, BiFunction<Integer, Player, String> message);
  void sendUpdating(long intervalTicks, Supplier<String> message);
  ```
- `com.falchus.lib.minecraft.spigot.player.elements.impl.Bossbar`
  ```java
  void send(BiFunction<Integer, Player, String> message, BiFunction<Integer, Player, Double> progress);
  void send(Supplier<String> message, Supplier<Double> progress);
  void sendUpdating(long intervalTicks, BiFunction<Integer, Player, String> message, BiFunction<Integer, Player, Double> progress);
  void sendUpdating(long intervalTicks, Supplier<String> message, Supplier<Double> progress);
  
  void setMessage(String message);
  void setProgress(double progress);
  ```
- `com.falchus.lib.minecraft.spigot.player.elements.impl.Chat`
  ```java
  void send(Supplier<String> prefix);
  void sendUpdating(long intervalTicks, Supplier<String> prefix);
  ```
- `com.falchus.lib.minecraft.spigot.player.elements.impl.Nametag`
  ```java
  void send(Supplier<String> prefix, Supplier<String> suffix);
  void sendUpdating(long intervalTicks, Supplier<String> prefix, Supplier<String> suffix);
  
  void setPrefix(String prefix);
  void setSuffix(String suffix);
  ```
- `com.falchus.lib.minecraft.spigot.player.elements.impl.Scoreboard`
  ```java
  void send(BiFunction<Integer, Player, String> title, BiFunction<Integer, Player, List<String>> lines);
  void send(Supplier<String> title, Supplier<List<String>> lines);
  void sendUpdating(long intervalTicks, BiFunction<Integer, Player, String> title, BiFunction<Integer, Player, List<String>> lines);
  void sendUpdating(long intervalTicks, Supplier<String> title, Supplier<List<String>> lines);
  
  void setTitle(String title);
  void setLines(List<String> lines);
  ```
- `com.falchus.lib.minecraft.spigot.player.elements.impl.Tablist`
  ```java
  void send(BiFunction<Integer, Player, List<String>> header, BiFunction<Integer, Player, List<String>> footer, Supplier<String> name);
  void send(Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name);
  void sendUpdating(long intervalTicks, BiFunction<Integer, Player, List<String>> header, BiFunction<Integer, Player, List<String>> footer, Supplier<String> name);
  void sendUpdating(long intervalTicks, Supplier<List<String>> header, Supplier<List<String>> footer, Supplier<String> name);
  
  void setHeader(List<String> header);
  void setFooter(List<String> footer);
  void setName(String name);
  ```

##### Builders
`com.falchus.lib.minecraft.spigot.utils.builder.EntityPlayerBuilder`
```java
EntityPlayerBuilder setName(String name);

EntityPlayerBuilder setUUID(UUID uuid);

EntityPlayerBuilder setSkin(String skinValue, String skinSignature);

EntityPlayerBuilder setInvisible(boolean invisible);

EntityPlayerBuilder setLocation(Location location);

EntityPlayerBuilder withInteractListener(Consumer<Player> onPlayerInteract);

EntityPlayerBuilder lookAtPlayer(boolean lookAtPlayer);

Object build();
```

`com.falchus.lib.minecraft.spigot.utils.builder.InventoryBuilder`
```java
InventoryBuilder(String title, Integer size);

InventoryBuilder dynamicSize(boolean dynamicSize);

InventoryBuilder setItem(int slot, ItemStack item);
InventoryBuilder setItem(int slot, ItemStack item, Consumer<Player> onClick);
InventoryBuilder setItem(int slot, ItemStack item, TriConsumer<Player, ItemStack, InventoryClickEvent> onClick);

InventoryBuilder setOpenAnimation(InventoryOpenAnimation openAnimation);

InventoryBuilder addItem(ItemStack item);
InventoryBuilder addItem(ItemStack item, Consumer<Player> onClick);
InventoryBuilder addItem(ItemStack item, TriConsumer<Player, ItemStack, InventoryClickEvent> onClick);

InventoryBuilder withClickListener(TriConsumer<Player, ItemStack, InventoryClickEvent> onClick);

InventoryBuilder fill(ItemStack filler);
InventoryBuilder fill();

void open(Player player);
void openPage(Player player, int page);

Inventory build();
List<Inventory> buildPages();
```

`com.falchus.lib.minecraft.spigot.utils.builder.ItemBuilder`
```java
ItemBuilder(Material material, int amount);
ItemBuilder(Material material);

ItemBuilder(com.falchus.lib.minecraft.spigot.enums.Material material, int amount);
ItemBuilder(com.falchus.lib.minecraft.spigot.enums.Material material);

ItemBuilder(ItemStack item);

ItemBuilder setName(String name);

ItemBuilder setLore(List<String> lore);

ItemBuilder addEnchantment(Enchantment enchantment, int level);

ItemBuilder addItemFlag(ItemFlag itemFlag);

ItemBuilder setDurability(short durability);

ItemBuilder setSkullOwner(String owner);
ItemBuilder setSkullTexture(String texture);

ItemBuilder setUUID(UUID uuid);

ItemBuilder withInteractListener(Consumer<Player> onPlayerInteract);
ItemBuilder withInventoryClickListener(TriConsumer<Player, ItemStack, InventoryClickEvent> onInventoryClick);

ItemStack build();
```

![](https://bstats.org/signatures/bukkit/FalchusLib.svg)
![](https://bstats.org/signatures/velocity/FalchusLib.svg)
</details>
