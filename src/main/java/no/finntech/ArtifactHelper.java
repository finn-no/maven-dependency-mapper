/* Copyright (2013) FINN.no AS
*
*   This is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version, with the Classpath Exception.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package no.finntech;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;

import java.util.HashMap;
import java.util.Map;


public class ArtifactHelper {
    static final String SEPARATOR = "#";
    public static String getId(Artifact artifact){
        return artifact.getGroupId() + ":"+ artifact.getArtifactId()+ ":" + artifact.getVersion();
    }

     public static String getId(Dependency dependency){
        return dependency.getGroupId() + ":"+ dependency.getArtifactId()+ ":" + dependency.getVersion();
    }

    public static Map<String,Object> getProperties(Artifact artifact){
        Map  propertyMap = new HashMap<String,Object>();
        propertyMap.put("name",getId(artifact));
        propertyMap.put("groupId",artifact.getGroupId());
        propertyMap.put("artifactId",artifact.getArtifactId());
        propertyMap.put("version",artifact.getVersion());
        propertyMap.put("groupIdAndArtifactId", artifact.getGroupId() + "#" + artifact.getArtifactId());
        propertyMap.put("prettyPrint", artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getVersion());
        return propertyMap;
    }

        public static Map<String,Object> getProperties(Dependency dependency){
        Map  propertyMap = new HashMap<String,Object>();
        propertyMap.put("name",getId(dependency));
        propertyMap.put("groupId",dependency.getGroupId());
        propertyMap.put("artifactId",dependency.getArtifactId());
        propertyMap.put("version",dependency.getVersion());
        propertyMap.put("groupIdAndArtifactId", dependency.getGroupId() + "#" + dependency.getArtifactId());
        propertyMap.put("prettyPrint", dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
        return propertyMap;
    }

    public static String getGroupIdAndArtifactId(Artifact artifact){
        return splice (artifact.getGroupId(), artifact.getArtifactId());
    }


    public static String splice(String ... s) {
        StringBuilder spliced = new StringBuilder();
        String separator = "";
        for(String tmp : s){
            spliced.append(separator).append(tmp);
            separator = SEPARATOR;

        }
        return spliced.toString();
    }

}
