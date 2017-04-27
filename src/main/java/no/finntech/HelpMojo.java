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
public class HelpMojo extends AbstractMojo {

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
