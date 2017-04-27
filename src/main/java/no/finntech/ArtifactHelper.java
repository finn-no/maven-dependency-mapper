package no.finntech;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;

public class ArtifactHelper {

    public static final String SEPARATOR = "#";
    public static final String COLON = ":";
    public static final String NAME_KEY = "name";
    public static final String GROUPID_KEY = "groupId";
    public static final String ARTIFACTID_KEY = "artifactId";
    public static final String VERSION_KEY = "version";
    public static final String GROUPID_ARTIFACTID_KEY = "groupIdAndArtifactId";
    public static final String PRETTYPRINT_KEY = "prettyPrint";
    public static final String CREATEDAT_KEY = "createdAt";

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private static String getId(Artifact artifact) {
    return artifact.getGroupId() + COLON + artifact.getArtifactId() + COLON + artifact.getVersion();
    }

    private static String getId(Dependency dependency) {
    return dependency.getGroupId() + COLON + dependency.getArtifactId() + COLON + dependency.getVersion();
    }

    public static Map<String, Object> getProperties(Artifact artifact) {
    Map<String, Object> propertyMap = new HashMap<String, Object>();
    propertyMap.put(NAME_KEY, getId(artifact));
    propertyMap.put(GROUPID_KEY, artifact.getGroupId());
    propertyMap.put(ARTIFACTID_KEY, artifact.getArtifactId());
    propertyMap.put(VERSION_KEY, artifact.getVersion());
    propertyMap.put(GROUPID_ARTIFACTID_KEY, artifact.getGroupId() + SEPARATOR + artifact.getArtifactId());
    propertyMap.put(PRETTYPRINT_KEY, artifact.getGroupId() + COLON + artifact.getArtifactId() + COLON + artifact.getVersion());
    propertyMap.put(CREATEDAT_KEY, FORMATTER.format(new Date()));
    return propertyMap;
    }

    public static Map<String, Object> getProperties(Dependency dependency) {
    Map<String, Object> propertyMap = new HashMap<String, Object>();
    propertyMap.put(NAME_KEY, getId(dependency));
    propertyMap.put(GROUPID_KEY, dependency.getGroupId());
    propertyMap.put(ARTIFACTID_KEY, dependency.getArtifactId());
    propertyMap.put(VERSION_KEY, dependency.getVersion());
    propertyMap.put(GROUPID_ARTIFACTID_KEY, dependency.getGroupId() + SEPARATOR + dependency.getArtifactId());
    propertyMap.put(PRETTYPRINT_KEY, dependency.getGroupId() + COLON + dependency.getArtifactId() + COLON + dependency.getVersion());
    propertyMap.put(CREATEDAT_KEY, FORMATTER.format(new Date()));
    return propertyMap;
    }

    public static String getGroupIdAndArtifactId(Artifact artifact) {
    return splice(artifact.getGroupId(), artifact.getArtifactId());
    }

    public static String splice(String... s) {
    StringBuilder spliced = new StringBuilder();
    String separator = "";
    for (String tmp : s) {
        spliced.append(separator).append(tmp);
        separator = SEPARATOR;
    }
    return spliced.toString();
    }

}
