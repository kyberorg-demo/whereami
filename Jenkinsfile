@Library('common-pipe@1-shared-pipeline')_

javaDockerPipeline(
    agent: [image: 'kyberorg/jobbari', tag: '1.4.1', args: '-u root --privileged'],
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    hubUser: 'kyberorg',
    skipMavenTests: true
)
