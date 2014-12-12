package no.finntech;

import org.neo4j.graphdb.RelationshipType;

public enum MavenRelationships implements RelationshipType {

    UNKONWN, COMPILE, PROVIDED, RUNTIME, TEST, SYSTEM, PLUGIN, IMPORT, PARENT;

    public static MavenRelationships getByName(String name) {
	if (name.equalsIgnoreCase("COMPILE")) {
	    return COMPILE;
	} else if (name.equalsIgnoreCase("PROVIDED")) {
	    return PROVIDED;
	} else if (name.equalsIgnoreCase("RUNTIME")) {
	    return RUNTIME;
	} else if (name.equalsIgnoreCase("TEST")) {
	    return TEST;
	} else if (name.equalsIgnoreCase("SYSTEM")) {
	    return SYSTEM;
	} else if (name.equalsIgnoreCase("PLUGIN")) {
	    return PLUGIN;
	} else if (name.equalsIgnoreCase("IMPORT")) {
	    return IMPORT;
	} else if (name.equalsIgnoreCase("PARENT")) {
	    return PARENT;
	} else {
	    return UNKONWN;
	}
    }
}
