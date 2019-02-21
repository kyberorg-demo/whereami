@Library('common-pipe@0.4.11')_

pipeline {
  agent {
    docker {
      reuseNode true
      image 'kyberorg/jobbari:1.5.0'
      args '-u root --privileged'
    }

  }
  stages {
    stage('Init n info') {
      parallel {
        stage('Runner info') {
          steps {
           runnerStatus()
          }
        }
        stage('Git info') {
          steps {
            sh '''##### Preparing git info #####
 echo ${GIT_COMMIT} > COMMIT 

'''
          }
        }
      }
    }
  } //stages
  environment {
    PROJECT = 'Who Am I'
    DOCKER_REPO = 'kyberorg/whoami'
    DOCKER_USER = 'kyberorg'
    DOCKER_HUB = credentials('docker-hub')
    terv = 'HabaHaba'
  }
}