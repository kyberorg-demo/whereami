@Library('common-pipe@0.6.4')_

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
          //sh label:'Maven Test', script:'mvn test -B'
          mavenTest()
        }
        junit(testResults: 'target/surefire-reports/**/*.xml', allowEmptyResults: true)
      } //steps end
    } //stage test end

    stage('Build') {
      steps {
        sh label:'Maven package', script:'mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true -B -V'
        sh label:'Make write for next step', script:'chmod ugo+w -R .'
      } //steps end
    } //stage Build end
    stage('Create Docker image') {
      steps {
        script {
          String dockerTag = makeDockerTag();
          makeDockerImage(dockerTag);
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