pipeline {
   agent {
        docker {
            image 'aayachi/maven-dind:1.0'
	        args '-u root --privileged'
        }
    }

   stages {
        stage('Build') {
            steps {
               dir('spring-project') {
               sh 'mvn clean install'
               sh 'docker build -t spring-app .'
               }
            }
        }

        stage('Deploy') {
            environment {
               DOCKER_HUB_CREDS = credentials('docker_hub_creds')
            }

            steps {
            sh 'docker login -u $DOCKER_HUB_CREDS_USR -p $DOCKER_HUB_CREDS_PSW'
            sh 'docker tag spring-app aayachi/spring-app-jenkins:1.0'
            sh 'docker push aayachi/spring-app-jenkins:1.0'
        }
    }
}
}
