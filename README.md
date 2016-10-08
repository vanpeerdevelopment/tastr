# tastr
tastr is the ultimate tool to help you taste and rate any wine you drink

# prerequisites
- [git](https://git-scm.com/)
- [java jdk8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
- [maven](https://maven.apache.org/)
- [virtualbox](https://www.virtualbox.org/) + [docker](https://www.docker.com/) or [postgres db](https://www.postgresql.org/) 

# technologies
## Ops
- [postgres db](https://www.postgresql.org/)

## Backend
- [flyway](https://flywaydb.org/)
- [spring boot](http://projects.spring.io/spring-boot/)
- [spring data](http://projects.spring.io/spring-data/)

# local postgres db
1. `ops/scripts/create-docker-vm.bat`
    - creates linux vm for docker with port forwarding of postgres port `5432`
2. `ops/scripts/start-developing.bat`
    - starts the linux vm if necessary and sets environment variables to point docker to the vm
    - creates postgres docker image and starts postgres container, username and password are `admin`, port `5432`

# build and develop
0. install all prerequisites and `ops/scripts/create-docker-vm.bat`
1. `git clone https://github.com/vanpeerdevelopment/tastr.git`
2. `ops/scripts/start-developing.bat`
3. `mvn initialize -PdbInit -pl tastr-infrastructure`
4. `mvn clean install`
