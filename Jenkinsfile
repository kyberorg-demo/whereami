@Library('common-pipe@1-shared-pipeline')_

javaDockerPipeline(
    agent: [image: 'kyberorg/jobbari', version: '1.5.0'],
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    hubUser: 'kyberorg',
    mavenBuildOptions: [addJavadoc: true, runTestsAsWell: false]
)
