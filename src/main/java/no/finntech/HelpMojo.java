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

/**
 * Goal which reads reverse dependency trees from a neo4j database
 *
 * @goal help
 * @threadSafe
 */
public class HelpMojo extends AbstractMojo{

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("target:help - prints this helptext");
        getLog().info("");
        getLog().info("target:store - stores dependency data in neo4j.");
        getLog().info("\tparameters: neo4jServer (defaults to http://localhost:7474)");
        getLog().info("");
        getLog().info("target:read - reads dependant artifacts from your neo4j database");
        getLog().info("\tparameters: neo4jServer (defaults to http://localhost:7474)");
    }
}

