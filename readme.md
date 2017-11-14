# Akka Split Brain Testing App

This is a simple app to demonstrate how split brain resolver works in Akka Clusters.

## Building the application
Perform the following steps:
* `mvn clean install` to build the application code
* `mvn docker:build` to build the image and tag it with the `latest` tag

The `akka-cluster-custom-downing_2.11` dependency is hosted by bintray (https://bintray.com/). For successful compilation, either add the dependency manually to your local repository by checking out the repository https://github.com/TanUkkii007/akka-cluster-custom-downing and building with `sbt` or configure maven to use bintray. To perform the latter, add the following to your `settings.xml` file:
```xml
   <profiles>
     <profile>
       <repositories>
         <repository>
           <snapshots>
              <enabled>false</enabled>
           </snapshots>
           <id>bintray</id>
           <name>bintray</name>
           <url>https://dl.bintray.com/tanukkii007/maven</url>
         </repository>
       </repositories>
       <pluginRepositories>
         <pluginRepository>
           <snapshots>
             <enabled>false</enabled>
           </snapshots>
           <id>bintray</id>
           <name>bintray-plugins</name>
           <url>https://dl.bintray.com/tanukkii007/maven</url>
          </pluginRepository>
        </pluginRepositories>
        <id>bintray</id>
     </profile>
   </profiles>

   <activeProfiles>
     <activeProfile>bintray</activeProfile>
   </activeProfiles>
```
