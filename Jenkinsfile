pipeline {
  agent {
       docker {
           image 'maven:3-alpine'
           args '-v ~/.m2:/root/.m2'
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
    stage('deploy') {
      steps {
        sh 'mvn deploy'
      }
    }
  }
}