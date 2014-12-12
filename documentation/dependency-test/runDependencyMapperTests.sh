##!/bin/sh

echo ""
echo "########################################################################################################"
echo "Build Maven dependency-mapper first"
cd ../..
mvn install

echo ""
echo "########################################################################################################"
echo "Run dependency-mapper analysis on project5 for dependency cycle test"
cd documentation/dependency-test/project5-without-cycle
mvn dependency-mapper:store

echo ""
echo "########################################################################################################"
echo "Run dependency-mapper analysis on multi-module project"
cd ..
mvn dependency-mapper:store

echo ""
echo "########################################################################################################"
echo "Run dependency-mapper analysis on project5 for dependency cycle test"
cd project5-with-cycle
mvn dependency-mapper:store

echo ""
echo "########################################################################################################"
echo "Check reverse dependencies for project5"
mvn dependency-mapper:read

echo ""
echo "########################################################################################################"
echo "Done"
echo "########################################################################################################"