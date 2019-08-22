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
                 String hookUrl = "https://docker.yatech.eu/api/webhooks/3328ba5f-6fea-4a51-a2b0-902e9f5c9573";  
                 deployToSwarm(hookUrl: hookUrl);
               } 
            }
        }
        stage('UI Tests') {
            steps {
               script {
                 String url = "https://kuka.yadev.eu"; 
                 smartWait(url: url);
                 testApp(url: url);
               } 
            }
        }
    }
    
}