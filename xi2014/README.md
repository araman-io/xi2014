1) Download Gradle 2.1 - Extract the zip folder; 
2) Add Graddle "bin" folder to path
3) Open CLI and check "gradle"
4) Once you have cloned the repository or everytime you have amended a file, go to the location of the build.gradle file and run "graldew build"
5) To run the app, do - gradlew build && java -jar build/libs/gs-spring-boot-0.1.0.jar
6) The name of the jar file, the version number all of them can be changed in the 'build.gradle' file. I just went with the flow.
7) Autowiring is still not working; Need to add the component scan at the root level