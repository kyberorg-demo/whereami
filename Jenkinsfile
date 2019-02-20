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
            sh ''' #### Gathering info ####
        
set +x
echo "Starting building ${PROJECT}"
echo ""
MV=`mvn --version`
DV=`docker --version`
        
# Internet available ?
wget -q --tries=10 --timeout=20 --spider http://google.com
if [ "$?" -eq "0" ]; then
        NET_STATUS="Host Online"
else
        NET_STATUS="Host Offline"
fi

echo "[Build info]"
echo "Git branch: ${GIT_BRANCH}"
echo "Git commit: ${GIT_COMMIT}"
echo "Jenkins Job #${BUILD_NUMBER}" 
echo "Jenkins Job URL: ${BUILD_URL}"
echo "Jenkins Tag: ${BUILD_TAG}"
echo ""
echo "[Worker info]"
echo "Hostname: ${HOSTNAME}"
echo "Net status: ${NET_STATUS}"
echo ""
echo "Docker version: ${DV}"
echo ""
echo "Maven version: ${MV}"
'''
          }
        }
        stage('Git info') {
          steps {
            sh '''##### Preparing git info #####
set +x set +e echo ${GIT_COMMIT} > COMMIT 
      echo $BRANCH_NAME > TAG 
echo ""
echo "" echo "### Git info ###"
COMMIT=`cat COMMIT`
TAG=`cat TAG`
echo "COMMIT: ${COMMIT}"           
echo "TAG: ${TAG}" 
'''
          }
        }
      }
    }
    stage('Test') {
      steps {
        timeout(time: 7) {
          sh 'mvn test -B'
        }

        junit(testResults: 'target/surefire-reports/**/*.xml', allowEmptyResults: true)
      }
    }
    stage('Build') {
      steps {
        sh 'mvn clean package -DskipTests=true -Dmaven.javadoc.skip=true -B -V'
        sh 'pwd && chmod ugo+w -R .'
      }
    }
    stage('Docker image') {
      steps {
        sh '''### Docker TAG ### set +x
set +e
case ${GIT_BRANCH} in
      master) DOCKER_TAG="stable" ;;
      trunk) DOCKER_TAG="latest" ;;
       PR-*) DOCKER_TAG="${GIT_BRANCH}" ;;
          *) DOCKER_TAG="${GIT_BRANCH}-latest" ;;
esac
 
echo "Docker Tag: ${DOCKER_TAG}"
echo ${DOCKER_TAG} > DOCKER_TAG
chmod ugo+w DOCKER_TAG
export DOCKER_TAG=${DOCKER_TAG}
cp DOCKER_TAG DOCKER_TAG.txt
'''
        archiveArtifacts 'DOCKER_TAG.txt'
        retry(count: 3) {
          sh '''### Create Docker image ###

set +x 
set +e
service docker start
DOCKER_TAG=`cat DOCKER_TAG`
echo "Building Docker image with: $DOCKER_TAG"
docker build -t $DOCKER_REPO:$DOCKER_TAG .'''
        }

      }
    }
  }
  environment {
    PROJECT = 'Who Am I'
    DOCKER_REPO = 'kyberorg/whoami'
    DOCKER_USER = 'kyberorg'
    DOCKER_HUB = credentials('docker-hub')
  }
}