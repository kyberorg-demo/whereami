@Library('common-pipe@5-allow-steps-override-or-add-remove')_

dasPipeline(
    dockerRepo: 'kyberorg/whoami',
    hubCredentialsId: 'docker-hub',
    gitlabConnectionId: 'gitlab',
    hubUser: 'kyberorg',
    skipMavenTests: false,
    stepTimeout: 3,
    
    preparationStep: {
        new ee.yadev.pipe.steps.PreparationStep().runStep();
        sh label: "Preparation", script: "echo This is custom preparationStep";
    },
    executionInfoStep: {
        new ee.yadev.pipe.steps.ExecutionInfoStep().buildInfo();
    },
)
