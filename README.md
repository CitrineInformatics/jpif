# JPIF

Java tools for working with Physical Information Files (http://www.citrine.io/pif).

This package includes java objects for all items in the Physical Information File (PIF). Files formatted in the PIF
schema can be serialized and deserialized using included methods.


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
