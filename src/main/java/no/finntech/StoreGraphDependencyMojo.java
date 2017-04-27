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


import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.index.RestIndex;

/**
 * Goal which stores dependency trees in neo4j database
 *
 * @goal store
 *
 * @phase process-sources
 *
 * @threadSafe
 */
public class StoreGraphDependencyMojo
        extends AbstractMojo {

    private static final String GROUP_ID_AND_ARTIFACT_ID = "groupIdAndArtifactId";
    private static final String COMPLETE_ID = "completeId";
    private static RestIndex<Node> index;
    private static final String ARTIFACT = "artifact";

    /**
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * Neo4J Server.
     * @parameter
     *   expression="${neo4jServer}"
     *   default-value="http://localhost:7474"
     */
    private String neo4jServer;

    RestAPI restAPI;

    public void execute() throws MojoExecutionException {
        final HashMap<String, String> config = new HashMap<String, String>();
        restAPI = new RestAPIFacade(neo4jServer + "/db/data");

        config.put("type", "exact");
        config.put("provider", "lucene");
        config.put("to_lower_case", "false");

        index = restAPI.createIndex(Node.class, ARTIFACT, config);

        getDependencies();
        getPlugins();
    }

    @SuppressWarnings("unchecked")
    private void getDependencies() {
        Node projectNode = makeNode(project.getArtifact());
        for(Relationship r:projectNode.getRelationships(Direction.OUTGOING)){
            r.delete();
        }
        if (project.getParentArtifact() != null) {
            registerDependency("parent", projectNode, project.getParentArtifact());
        }
        registerDependencies(project.getDependencies());
    }

    private void registerDependencies(List<Dependency> dependencies) {

        Node projectNode = makeNode(project.getArtifact());

        for (Dependency dependency : dependencies) {
            registerDependency(projectNode, dependency);
        }
    }

    private Node makeNode(Artifact artifact) {
        String completeId = ArtifactHelper.splice(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion());
        Node projectNode = restAPI.getOrCreateNode(index, COMPLETE_ID, completeId, ArtifactHelper.getProperties(artifact));
        restAPI.getIndex(ARTIFACT).add(projectNode, GROUP_ID_AND_ARTIFACT_ID, ArtifactHelper.splice(artifact.getGroupId(), artifact.getArtifactId()));
        restAPI.getIndex(ARTIFACT).add(projectNode, COMPLETE_ID, completeId);
        return projectNode;
    }

    private Node makeNode(Dependency dependency) {
        String completeId = ArtifactHelper.splice(dependency.getGroupId(), dependency.getArtifactId(), dependency.getVersion());
        Node projectNode = restAPI.getOrCreateNode(index, COMPLETE_ID, completeId, ArtifactHelper.getProperties(dependency));
        restAPI.getIndex(ARTIFACT).add(projectNode, GROUP_ID_AND_ARTIFACT_ID, ArtifactHelper.splice(dependency.getGroupId(), dependency.getArtifactId()));
        return projectNode;
    }

    private void registerDependency(Node projectNode, final Dependency dependency) {
        Node artifactNode;
        String scope = dependency.getScope();

        if (scope == null) {
            scope = "compile"; // default scope
        }
        try {
            artifactNode = makeNode(dependency);
            projectNode.createRelationshipTo(artifactNode, MavenRelationships.getByName(scope));
            getLog().info("Registered dependency to " + ArtifactHelper.getId(dependency) + ", scope: " + scope);
        } catch (Throwable e) {
            getLog().error(e.getMessage(), e);
        }
    }

    private void registerDependencies(Set<Artifact> artifacts, String type) {
        Node projectNode = makeNode(project.getArtifact());

        for (Artifact artifact : artifacts) {
            registerDependency(type, projectNode, artifact);
        }
    }

    private void registerDependency(String type, Node projectNode, Artifact artifact) {
        projectNode.createRelationshipTo(makeNode(artifact), MavenRelationships.getByName(type));
        getLog().info("Registered dependency of scope: " + type);
    }

    private void getPlugins() {
        @SuppressWarnings("unchecked")
        Set<Artifact> plugins = project.getPluginArtifacts();
        registerDependencies(plugins, "plugin");
    }
}
