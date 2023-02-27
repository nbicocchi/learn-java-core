# Object Oriented Programming - DIEF/UNIMORE

## Java Exercises (I/O)

**[FileAttributes.java]** Write a set of methods accepting a String representing a file name and returning various attributes of the file (see java.nio.file.Files, java.nio.file.Paths).

The methods have the following prototypes:

```
public static boolean exists(String filename);
public static boolean isReadable(String filename);
public static boolean isWritable(String filename);
public static boolean isExecutable(String filename);
public static boolean isRegularFile(String filename);
public static boolean isDirectory(String filename);
public static long size(String filename) throws IOException;

```

where:

* **filename** is the name of the file

---

**[FileCopyMove.java]** Write two methods accepting two strings representing a source and a destination paths for respectively copying and moving the source to the destination (see java.nio.file.Files, java.nio.file.Paths).

The methods have the following prototypes:

```
public static void fileCopy(String src, String dst) throws IOException;
public static void fileMove(String src, String dst) throws IOException;
```

where:

* **src** is the path of the source file
* **dst** is the path of the destination file

---

**[FileCreateDelete.java]** Write two methods accepting a string representing a path for, respectively, creating an empty file and deleting it (see java.nio.file.Files, java.nio.file.Paths)

The methods have the following prototypes:

```
public static void createFile(String src) throws IOException;
public static void deleteFile(String src) throws IOException;
```

where:

* **src** is the path of the file

---

**[FileList.java]** Write a method accepting a string representing a directory path returning a ```Set<String>``` representing the names of the files (not directories!) contained within (see java.nio.file.Files, java.nio.file.Paths)

The method has the following prototype:

```
public static Set<String> fileList(String src) throws IOException;
```

where:

* **src** is the path of the directory

---

**[PortableDesktopPath.java]** Write a method returning a String representing the path of the Desktop folder. The path must not be hardcoded within the method but computed in a portable way (the same .class file must work on both Unix and Windows). You can assume that the desktop folder is a subdirectory (named "Desktop") inside the user's home directory (see Java System Properties).

The method has the following prototype:

```
public static String portableDesktopPath();
```

---

**[ReadWriteBinary.java]** Write two methods making use of a ```byte[]```for reading and writing a binary file (see java.nio.file.Files, java.nio.file.Paths).

The first method has the following prototype:

```
public static byte[] readBinaryFile(String filename) throws IOException;
```

where:

* **filename** is the path of the file to be read.

The second method has the following prototype:

```
public static void writeBinaryFile(String filename, byte[] bytes) throws IOException;
```

where:

* **filename** is the path of the file to be written.
* **byte** contains the data to be written.

---

**[ReadWriteText.java]** Write two methods making use of a ```List<String>```for reading and writing a text file (see java.nio.file.Files, java.nio.file.Paths).

The first method has the following prototype:

```
public static List<String> readTextFile(String filename) throws IOException;
```

where:

* **filename** is the path of the file to be read.

The second method has the following prototype:

```
public static <T extends CharSequence> void writeTextFile(String filename, List<T> lines) throws IOException;
```

where:

* **filename** is the path of the file to be written.
* **line** contains the lines to be written.

---

**[CopyBinaryLong.java]** Write a method for copying a large binary file without requiring the allocation of the entire file in memory. The method reads blocks of bytes from the source file and writes them to the destination file using a buffer as an intermediate medium (see java.nio.ByteBuffer, java.nio.channels.FileChannel).

The method has the following prototype:

```
public static void copyBinaryLong(String src, String dst);
```

where:

* **src** is the path of the source file.
* **dst** is the path of the destination file.

---

**[CopyTextLong.java]** Write a method for copying a large text file without requiring the allocation of the entire file in memory. The method reads all lines from the source file and writes them to the destination file using a String as an intermediate medium (see java.io.BufferedReader, java.nio.file.Files).

The method has the following prototype:

```
public static void copyTextLong(String src, String dst);
```

where:

* **src** is the path of the source file.
* **dst** is the path of the destination file.
