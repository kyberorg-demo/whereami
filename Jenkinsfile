@Library('common-pipe@5-allow-steps-override-or-add-remove')_

buildPipeline(
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    gitlabConnectionId: 'gitlab',
    hubUser: 'kyberorg',
    skipMavenTests: true,
    stepTimeout: 3,
    
    preparationStep: {
        new ee.yadev.pipe.steps.PreparationStep().runStep();
        sh label: "Preparation", script: "echo This is custom preparationStep";
    },
    executionInfoStep: {
        new ee.yadev.pipe.steps.ExecutionInfoStep().buildInfo();
    },
)
