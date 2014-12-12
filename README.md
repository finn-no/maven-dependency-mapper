## Maven dependency mapper

* [Example project and Screenshots](documentation/README.md)

Stores dependency relationships in a graph database (neo4j). This maven plugin provides two operations:

```
# stores all dependencies in neo4j graph db
mvn dependency-mapper:store

# reads all reverse dependencies from neo4j graph db
mvn dependency-mapper:read
```

### Local environment

* install neo4j http://www.neo4j.org/develop#install
* checkout this repo
* mvn install
* Go to a maven project and run *mvn dependency-mapper:store*
* To find out who is depending on your project do "mvn dependency-mapper:read"

Override neo4j server setting with *-Dneo4jServer=http://yourserver:yourport*, defaults to *http://localhost:7474*

### Example output

```
mvn nodependency-mapper:read 

[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building greenpages thrift-client 3.4.5-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- dependency-mapper-maven-plugin:1.0-SNAPSHOT:read (default-cli) @ commons-thrift-client ---
[INFO] Resolving reverse dependencies
[INFO] no.finntech.travel.supplier:supplier-client:1.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.1.1
[INFO] no.finntech.cop:client:1.1-SNAPSHOT -> no.finntech:commons-thrift-client:3.1.1
[INFO] no.finntech.oppdrag-services:iad-model:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
[INFO] no.finntech:minfinn:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
...
[INFO] no.finntech.attribute:attribute-client:1.2.3-SNAPSHOT -> no.finntech:commons-thrift-client:3.3
[INFO] no.finntech.organisation:organisation-client:1.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.4
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.957s
[INFO] Finished at: Thu Jan 31 09:50:19 CET 2013
[INFO] Final Memory: 9M/211M
[INFO] ------------------------------------------------------------------------
```

### To include this in your project

Put this in you pom.xml

```
    <plugin>
        <groupId>no.finntech</groupId>
        <artifactId>dependency-mapper-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
        <configuration>
            <neo4jServer>http://neo4jServer:7474</neo4jServer>
        </configuration>
    </plugin>
```

Now you can use the plugin with *mvn dependency-mapper:store* and *mvn dependency-mapper:read*
