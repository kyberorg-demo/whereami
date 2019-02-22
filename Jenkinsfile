@Library('common-pipe@0.7.6')_

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
        stage('Build info') {
          steps {
             buildInfo() 
          }
        } 
      } //parallel end
    } //init n info stage

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
    stage('Create Docker image') {
      steps {
        script {
          String dockerTag = makeDockerTag();
          makeDockerImage(dockerRepo: env['DOCKER_REPO'], dockerTag: dockerTag);
        } //script end
      } //steps
    } //stage Create Docker image end
  } //stages
  environment {
    PROJECT = 'Who Am I'
    DOCKER_REPO = 'kyberorg/whoami'
    DOCKER_USER = 'kyberorg'
    DOCKER_HUB = credentials('docker-hub')
  } //environment end
} //pipeline end