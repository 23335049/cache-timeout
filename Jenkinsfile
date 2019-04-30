pipeline {
  agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
  }
  stages {
    stage('build') {
      steps {
        sh 'mvn build'
      }
    }
    stage('test') {
      steps {
        sh 'mvn test'
      }
    }
  }
}