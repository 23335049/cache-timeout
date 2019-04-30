pipeline {
  agent any
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