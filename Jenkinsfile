@Library('common-lib@master')_
pipeline {
    agent any;
    stages {
        stage('Vaadin') {
            steps {
               script {
                 //vaadin(prodModeProfile: 'production-mode',extraProfiles: 'noTesting')
                 vaadin(extraProfiles: 'noTesting')
                 //archiveArtifacts(artifacts: 'target/whereami.jar') 
               } 
            }
        }
        stage('Docker') {
            steps {
               script {
                   def repo = 'kyberorg/whereami';
                   def tags = ['latest'];
                   //def tags = ['latest', 'debug'];
                   dockerBuild(registry: 'docker.io', repo: repo, tags: tags);
                   dockerLogin(creds: 'docker-hub');
                   dockerPush();
                   dockerLogout();
                   dockerClean();
               } 
            }
        }
        stage('Deploy') {
            steps {
               script {
                 deployToSwarm();
               } 
            }
        }
    }
    
}