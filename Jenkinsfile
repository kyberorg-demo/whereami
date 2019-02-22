@Library('common-pipe@0.9.0')_

pipeline {
  agent {
    docker {
      reuseNode true
      image 'kyberorg/jobbari:1.5.0'
      args '-u root --privileged'
    }

  }
  stages {
    stage('Execution info') {
      steps {
        runnerStatus();
        buildInfo();
      }
    } // Stage 'Execution info' end 

    stage('Test') {
      steps {
        timeout(time: 7) {
          mavenTest()
        }
        junit(testResults: 'target/surefire-reports/**/*.xml', allowEmptyResults: true)
      } //steps end
    } //stage test end

    stage('Build') {
      steps {
        mavenBuild()
        sh label:'Make all write for cleanup', script:'chmod ugo+w -R .'
      } //steps end
    } //stage Build end

    stage('Docker: Build and Push') {
      steps {
        dockerStart();
        dockerLogin(env.DOCKER_REPO_CREDS_ID);
        dockerBuild(env.DOCKER_REPO, getDockerTag()); 
        dockerPush(env.DOCKER_REPO);
      } //steps end
    } //stage 'Docker: Build and Push' end

  } //stages
  environment {
    DOCKER_REPO = 'kyberorg/whoami'
    DOCKER_REPO_CREDS_ID = 'docker-hub'
  } //environment end
} //pipeline end