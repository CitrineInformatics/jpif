# JPIF

Java tools for working with Physical Information Files (http://www.citrine.io/pif).

This package includes java objects for all items in the Physical Information File (PIF). Files formatted in the PIF
schema can be serialized and deserialized using included methods.

## Compilation

It is possible to build a jar for this package using `mvn clean install`.

An uber jar containing all of the dependencies can be created using `mvn clean install -Pvalidator`. This jar can in 
turn be executed using `java -jar target/jpif-validator.jar path/to/pif [max records to validate]` in order to validate the 
content of a file that contains PIF records.

An uber jar containing all of the dependencies can be created using `mvn clean install -Pparser`. This jar can in 
turn be executed using `java -jar target/jpif-parser.jar path/to/pif` in order to read the content of a PIF file, convert to
 a rigid schema, and print to standard out.


## Usages

### Reading PIF-formatted sources

Given an input stream, reader, or string, PIF systems can be read using e.g.

```java
PifObjectStream pifObjectStream = new PifObjectStream(inputStream);
for (System system : pifObjectStream) {
    // Do work on system
}
pifObjectStream.close();
```

### Writing PIF records

Writing a single PIF system to a string:

```java
System system = new System();
String systemString = PifObjectMapper.getInstance().writeValueAsString(system);
```

Writing a list of PIF systems to a string:

```java
List<System> systems = new ArrayList<>();
String systemsString = PifObjectMapper.getInstance().getSystemListWriter().writeValueAsString(systems);
```

Converting a PIF system, list of PIF systems, or `PifObjectStream` to an `InputStream`:

```java
InputStream inputStream = new PifInputStream(pifObjectStream);
```
