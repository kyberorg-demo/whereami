@Library('common-pipe@1-shared-pipeline')_

javaDockerPipeline(
    agent: [image: 'kyberorg/jobbari', tags: '1.5.0', args: '-u root --privileged'],
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    hubUser: 'kyberorg',
    skipMavenTests: true
)
