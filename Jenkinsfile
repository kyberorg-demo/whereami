@Library('common-pipe@0.10.2')_

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

    stage('JavaApp: Build and Test') {
      steps {
        timeout(time: 7) {
          mavenTest();
        }
        testReport();
        mavenBuild();
        writableWorkspace();

      } //steps end
    } //stage 'JavaApp: Build and Test' end

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
    FOR_USER = 'kyberorg' //this should be equal to Docker Hub User to make it sensitive and therefore hide commands
  } //environment end
} //pipeline end