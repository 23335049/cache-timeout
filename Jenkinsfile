pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /Users/wangn/.m2:/root/.m2'
    }

  }
  stages {
    stage('test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('deploy') {
      steps {
        sh 'mvn deploy -DskipTests'
      }
    }
  }
}