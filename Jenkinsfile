@Library('common-pipe@5-allow-steps-override-or-add-remove')_

javaDockerPipeline(
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    gitlabConnectionId: 'gitlab',
    hubUser: 'kyberorg',
    skipMavenTests: true,
    
    preparationStep: {
        sh label: "Test", script: "echo Custom";
    }
)
