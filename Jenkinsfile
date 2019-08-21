@Library('common-lib@master')_
pipeline {
    agent any;
    stages {
        stage('Vaadin') {
            steps {
               script {
                 vaadin(prodModeProfile: 'production-mode', extraProfiles: 'noTesting', label: 'Build Vaadin in Prod Mode')
                 archiveArtifacts(artifacts: 'target/whereami.jar') 
               } 
            }
        }
    }
}