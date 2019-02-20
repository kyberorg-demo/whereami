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

sh ''' #### Git info ####

set +x
set +e


echo ${GIT_COMMIT}
echo $BRANCH_NAME



echo "TODO to be done"

'''
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