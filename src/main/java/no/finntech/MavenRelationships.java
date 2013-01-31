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

import org.neo4j.graphdb.RelationshipType;

public enum MavenRelationships implements RelationshipType {
    UNKONWN,
    COMPILE,
    PROVIDED,
    RUNTIME,
    TEST,
    SYSTEM,
    PLUGIN,
    IMPORT,
    PARENT;

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
