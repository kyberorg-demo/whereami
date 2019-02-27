@Library('common-pipe@1-shared-pipeline')_

javaDockerPipeline(
    agent: [image: 'kyberorg/jobbari', version: '1.5.0'],
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    //hubUser: 'kyberorg',
    skipMavenTests: true,
    mavenTestsTimeout: 1, //minutes 
    mavenBuildOptions: [addJavadoc: false, runTestsAsWell: false],
    useDebugTag: false
)
