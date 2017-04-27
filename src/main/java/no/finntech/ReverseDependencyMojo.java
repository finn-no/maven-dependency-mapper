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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestAPIFacade;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.index.RestIndex;

/**
 * Goal which reads reverse dependency trees from a neo4j database
 *
 * @goal read
 *
 * @phase process-sources
 *
 * @threadSafe
 */
public class ReverseDependencyMojo extends AbstractMojo{

    private static final String GROUP_ID_AND_ARTIFACT_ID = "groupIdAndArtifactId";
    private static final String PRETTY_PRINT = "prettyPrint";

    /**
     *
     *
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

    /**
     * Neo4J username.
     * @parameter
     *   expression="${neo4jUser}"
     */
    private String neo4jUser;

    /**
     * Neo4J password.
     * @parameter
     *   expression="${neo4jPass}"
     */
    private String neo4jPass;

    RestAPI restAPI;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Resolving reverse dependencies");

        if (neo4jUser == null) {
            restAPI = new RestAPIFacade(neo4jServer + "/db/data");
        } else {
            restAPI = new RestAPIFacade(neo4jServer + "/db/data", neo4jUser, neo4jPass);
        }

        listDependants();
    }

    private void listDependants(){
        final RestIndex<RestNode> index = restAPI.getIndex("artifact");
        final IndexHits<RestNode> nodes = index.query(GROUP_ID_AND_ARTIFACT_ID, ArtifactHelper.getGroupIdAndArtifactId(project.getArtifact()));
        for (RestNode node : nodes) {
            listNodeDependants(node);
        }

    }

    private void listNodeDependants(RestNode dependency) {
        for (Relationship r : dependency.getRelationships(Direction.INCOMING)) {
            getLog().info(r.getStartNode().getProperty(PRETTY_PRINT) + " -> " + r.getEndNode().getProperty(PRETTY_PRINT));
        }
    }


}
