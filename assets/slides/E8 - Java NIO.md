# Java NIO

### Stream Oriented vs. Buffer Oriented
Java IO being stream oriented means that you read one or more bytes at a time, from a stream. What you do with the read bytes is up to you. They are not cached anywhere. Furthermore, you cannot move forth and back in the data in a stream. If you need to move forth and back in the data read from a stream, you will need to cache it in a buffer first.

Java NIO's buffer oriented approach is slightly different. Data is read into a buffer from which it is later processed. You can move forth and back in the buffer as you need to. This gives you a bit more flexibility during processing. However, you also need to check if the buffer contains all the data you need in order to fully process it. And, you need to make sure that when reading more data into the buffer, you do not overwrite data in the buffer you have not yet processed.

### Blocking vs. Non-blocking IO
Java IO's various streams are blocking. That means, that when a thread invokes a read() or write(), that thread is blocked until there is some data to read, or the data is fully written. The thread can do nothing else in the meantime.

Java NIO's non-blocking mode enables a thread to request reading data from a channel, and only get what is currently available, or nothing at all, if no data is currently available. Rather than remain blocked until data becomes available for reading, the thread can go on with something else.

The same is true for non-blocking writing. A thread can request that some data be written to a channel, but not wait for it to be fully written. The thread can then go on and do something else in the mean time.

What threads spend their idle time on when not blocked in IO calls, is usually performing IO on other channels in the meantime. That is, a single thread can now manage multiple channels of input and output.

---

### java.nio.file.Path
A Java `Path` instance represents a *path* in the file system. A path can point to either a file or a directory. A path can be absolute or relative.

The fully qualified name of the Java `Path` interface is `java.nio.file.Path`.

In many ways the `java.nio.file.Path` interface is similar to the [`java.io.File`](https://jenkov.com/java-io/file.html) class, but there are some minor differences. 

You create a `Path` instance using a static method in the `Paths` class named `Paths.get()`.

```
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) {
        Path path = Paths.get("c:\\data\\myfile.txt");
    }
}
```

Creating an absolute path is done by calling the `Paths.get()` factory method with the absolute file as parameter. Here is an example of creating a `Path` instance representing an absolute path:

```
Path path = Paths.get("c:\\data\\myfile.txt");
```

The Java NIO `Path` class can also be used to work with relative paths. You create a relative path using the `Paths.get(basePath, relativePath)` method. Here are two relative path examples in Java:

```
Path p1 = Paths.get("d:\\data", "projects");
Path p1 = Paths.get("d:\\data", "projects\\a-project\\myfile.txt");
```

The first example creates a Java `Path` instance which points to the path (directory) `d:\data\projects`. The second example creates a `Path` instance which points to the path (file) `d:\data\projects\a-project\myfile.txt` .

### Path.relativize()
The Java Path method `relativize()` can create a new Path which represents the second Path relative to the first Path. 

For instance, with the path `/data` and `/data/subdata/subsubdata/myfile.txt"`, the second path can be expressed as `/subdata/subsubdata/myfile.txt` relative to the first path. 

```
Path basePath = Paths.get("/data");
Path path     = Paths.get("/data/subdata/subsubdata/myfile.txt");

Path basePathToPath = basePath.relativize(path);
Path pathToBasePath = path.relativize(basePath);

System.out.println(basePathToPath);
System.out.println(pathToBasePath);

// Output:
// subdata/subsubdata/myfile.txt
// ../../..
```

### Path.normalize()
The `normalize()` method of the `Path` interface can normalize a path. Normalizing means that it removes all the `.` and `..` codes in the middle of the path string, and resolves what path the path string refers to. 

```
String originalPath =
"d:\\data\\projects\\a-project\\..\\another-project";

Path path1 = Paths.get(originalPath);
System.out.println("path1 = " + path1);

Path path2 = path1.normalize();
System.out.println("path2 = " + path2);

// Output:
// path1 = d:\data\projects\a-project\..\another-project
// path2 = d:\data\projects\another-project
```

---

### java.nio.file.Files
The Java NIO `Files` class provides several methods for manipulating files in the file system. 

The `Files` class contains many methods, so check the JavaDoc too, if you need a method that is not described here. 

The `java.nio.file.Files` class works with `java.nio.file.Path` instances, so you need to understand the `Path` class before you can work with the `Files` class.

### Files.exists()
Since `Path` instances may or may not point to paths that exist in the file system, you can use the `Files.exists()` method to determine if they do (in case you need to check that).

```
Path path = Paths.get("data/logging.properties");

boolean pathExists = Files.exists(path, new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
```

Notice the second parameter of the `Files.exists()` method. This parameter is an array of options that influence how the `Files.exists()` determines if the path exists or not. In this example above the array contains the `LinkOption.NOFOLLOW_LINKS` which means that the `Files.exists()` method should not follow symbolic links in the file system to determine if the path exists.

### Files.is*
The `Files` class provides a set of methods for verifying files properties.

* isRegularFile(Path path)
* isDirectory(Path path)
* isReadable(Path path)
* isWritable(Path path)
* isExecutable(Path path)

### Files.create*
The `Files.createFile()` method creates a new file from a `Path` instance.

```
Path path = Paths.get("data/subdir/output.log");

try {
    Path newFile = Files.createFile(path);
} catch (FileAlreadyExistsException e) {
    // the file already exists.
} catch (IOException e) {
    // something else went wrong
}
```

The `Files.createDirectory()` method creates a new directory from a `Path` instance. 

```
Path path = Paths.get("data/subdir");

try {
    Path newDir = Files.createDirectory(path);
} catch (FileAlreadyExistsException e) {
    // the directory already exists.
} catch (IOException e) {
    // something else went wrong
}
```

If creating the file or the directory succeeds, a `Path` instance is returned which points to the newly created path.

### Files.copy()
The `Files.copy()` method copies a file from one path to another. 

```
Path sourcePath = Paths.get("data/logging.properties");
Path destinationPath = Paths.get("data/logging-copy.properties");

try {
    Files.copy(sourcePath, destinationPath);
} catch (FileAlreadyExistsException e) {
    // destination file already exists
} catch (IOException e) {
    // something else went wrong
}
```

First the example creates a source and destination `Path` instance. Then the example calls `Files.copy()`, passing the two `Path` instances as parameters. This will result in the file referenced by the source path to be copied to the file referenced by the destination path.

### Files.move()
The `Files.move()` method moves a file from one path to another.
```
Path sourcePath      = Paths.get("data/logging-copy.properties");
Path destinationPath = Paths.get("data/subdir/logging-moved.properties");

try {
    Files.move(sourcePath, destinationPath,
        StandardCopyOption.REPLACE_EXISTING);
} catch (IOException e) {
    //moving file failed.
}
```

First the source path and destination path are created. The source path points to the file to move, and the destination path points to where the file should be moved to. Then the `Files.move()` method is called. This results in the file being moved.

Notice the third parameter passed to `Files.move()` . This parameter tells the `Files.move()` method to overwrite any existing file at the destination path. This parameter is actually optional.

### Files.delete()
The `Files.delete()` method can delete a file or directory. 

```
Path path = Paths.get("data/subdir/logging-moved.properties");

try {
    Files.delete(path);
} catch (IOException e) {
    //deleting file failed
}
```

First the `Path` pointing to the file to delete is created. Second the `Files.delete()` method is called. If the `Files.delete()` fails to delete the file for some reason (e.g. the file or directory does not exist), an `IOException` is thrown.

### Files.list()
The `Files.list()` method returns a `Stream<Path>` representing the entries in the directory. The listing is not recursive.

```
Path srcPath = Paths.get(src);
try (Stream<Path> stream = Files.list(srcPath)) {
    stream.forEach(System.out::println);
}
```

First the `Path` pointing to the directory to be listed is created. Second the `Files.list()` returns a stream of `Path` objects.

### Files.readAllBytes()
Reads all the bytes from a file. The method ensures that the file is closed when all bytes have been read or an I/O error, or other runtime exception, is thrown.

Note that this method is intended for simple cases where it is convenient to read all bytes into a byte array. It is not intended for reading in large files.

```
Path src = Paths.get(filename);
byte[] buffer = Files.readAllBytes(src);
```

### Files.readAllLines()
Read all lines from a file. Bytes from the file are decoded into characters using the UTF-8 charset.

Note that this method is intended for simple cases where it is convenient to read all lines at once. It is not intended for reading in large files.

```
Path src = Paths.get(filename);
List<String> lines = Files.readAllLines(src);
```

### Files.write()