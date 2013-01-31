Maven dependency mapper
=======================
Stores dependency relationships in a graph database (neo4j)
-----------------------------------------------------------

Local environment:
------------------
* install neo4j http://www.neo4j.org/develop#install
* checkout this repo
* mvn install
* Go to a maven project and run "mvn no.finntech:maven-dependency-mapper:1.0-SNAPSHOT:store"
* Do a ""mvn no.finntech:maven-dependency-mapper:1.0-SNAPSHOT:read"

Override neo4j server setting with -Dneo4jServer=http://yourserver:yourport, defaults to http://localhost:7474


Example output:
---------------
mvn no.finntech:maven-dependency-mapper:1.0-SNAPSHOT:read 
[INFO] Scanning for projects...
[INFO]                                                                         
[INFO] ------------------------------------------------------------------------
[INFO] Building greenpages thrift-client 3.4.5-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- maven-dependency-mapper:1.0-SNAPSHOT:read (default-cli) @ commons-thrift-client ---
[INFO] Resolving reverse dependencies
[INFO] no.finntech.travel.supplier:supplier-client:1.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.1.1
[INFO] no.finntech.cop:client:1.1-SNAPSHOT -> no.finntech:commons-thrift-client:3.1.1
[INFO] no.finntech.oppdrag-services:iad-model:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
[INFO] no.finntech:minfinn:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
[INFO] no.finntech:service-user:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
[INFO] no.finntech:service-oppdrag:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
[INFO] no.finntech:kernel:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.3
[INFO] no.finntech.travelstats:travelstats-collector:1.7.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech.travelstats:travelstats-server:1.7.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech.travelstats:travelstats-db:1.7.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech.travelstats:travelstats-common:1.7.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech.travelstats:travelstats-api:1.7.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech:travelstats:1.7.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech:kart:12.10.1-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech:currency-client:2.1-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech.attribute:attribute-client:1.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.7
[INFO] no.finntech.vetting:vetting-client:3.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.user:user-client:0.6.4-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.user:user-server:0.6.4-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.objectrelation:objectrelation-client:1.3-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech:myinbox-client:1.2.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.mail:mail-test-api:3.4-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.mail:mail-client:3.4-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.api:finn-api-war:2013.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.cardata:cardata-client:1.4-SNAPSHOT -> no.finntech:commons-thrift-client:3.4.2
[INFO] no.finntech.broadcast:broadcast-client:3.1.2-SNAPSHOT -> no.finntech:commons-thrift-client:3.0.1
[INFO] no.finntech:cms-client:1.0-SNAPSHOT -> no.finntech:commons-thrift-client:3.2.4
[INFO] no.finntech:countstatistics-count-reduce:3.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.3
[INFO] no.finntech:countstatistics-count-listener:3.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.3
[INFO] no.finntech:countstatistics-count-impl:3.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.3
[INFO] no.finntech.attribute:attribute-client:1.2.3-SNAPSHOT -> no.finntech:commons-thrift-client:3.3
[INFO] no.finntech.organisation:organisation-client:1.6-SNAPSHOT -> no.finntech:commons-thrift-client:3.4
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.957s
[INFO] Finished at: Thu Jan 31 09:50:19 CET 2013
[INFO] Final Memory: 9M/211M
[INFO] ------------------------------------------------------------------------


