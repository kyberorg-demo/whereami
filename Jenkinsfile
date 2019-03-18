@Library('common-pipe@5-allow-steps-override-or-add-remove')_

javaDockerPipeline(
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    gitlabConnectionId: 'gitlab',
    hubUser: 'kyberorg',
    skipMavenTests: true,
    
    preparationStep: {
        new ee.yadev.pipe.steps.PreparationStep().runStep();
        sh label: "Preparation", script: "echo This is custom preparationStep";
    },
    executionInfoStep: {
        new ee.yadev.pipe.steps.ExecutionInfoStep().buildInfo();
    },
    buildJavaAppStep: {
        new ee.yadev.pipe.steps.BuildJavaAppStep().mavenBuild();

    },
    dockerStep: {
        new ee.yadev.pipe.steps.DockerStep().dockerStart();
    }, 
)
