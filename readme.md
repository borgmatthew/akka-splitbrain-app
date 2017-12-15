# Split Brain Scenarios in Akka Clusters

This is a simple application to create split brain scenarios in akka
clusters, and how a split brain resolver handles such scenarios.
The purpose of this application is to calculate the IQ of a person,
given a name.

The following are specific requirements for this application:
* High availability - millions of people may be asking for their IQ at
the same time. There should be no downtime, otherwise the app loses
popularity.
* Consistent - once an IQ value is assigned to a name, it doesn't change

## Application Design

The application is composed of 3 services:
* zookeeper - used for service discovery and cluster setup
* einstein - a service which calculates the IQ, given a person's name.
Named after Albert Einstein, as he was very intelligent, hence capable
to quickly assign an IQ value to a person's name
* nginx - receives all http requests and forwards them to the einstein
service

To fulfill the first requirement, this service was designed to be part
of a cluster where multiple einstein services may be running at the same
time.

To fulfill the second requirement, the einstein service utilises the
actor model to represent names. An actor is assigned a name and is
responsible to calculate the IQ and return the same value whenever asked
for it. This is used alongside cluster sharding, to make sure that only
one actor for the same name exists within the cluster.

## Building the application
Perform the following steps:
* `mvn clean install` to build the application code
* `mvn docker:build` to build the image and tag it with the `latest` tag

The `akka-cluster-custom-downing_2.11` dependency is hosted by bintray
(https://bintray.com/). For successful compilation, either add the
dependency manually to your local repository by checking out the
repository https://github.com/TanUkkii007/akka-cluster-custom-downing
and building with `sbt` or configure maven to use bintray. To perform
the latter, add the following to your `settings.xml` file:
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

## Running the application
To run this application, make sure that you have docker and
docker-compose installed.

### Starting the application
Navigate to the `docker` directory and run the
following command:

`docker-compose -f application.yml up`

To run multiple instances of the `einstein` service:

`docker-compose -f application.yml up --scale einstein=3 einstein
zookeeper nginx`

### Stopping the application
Navigate to the `docker` directory and run the
following command:

`docker-compose -f application.yml down`

### Asking for an IQ
To get an IQ value for a name, perform a `GET` request on the following
URL:

`http://localhost:8080/iq/{name}`