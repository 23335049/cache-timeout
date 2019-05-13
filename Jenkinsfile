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
   stage('SonarQube analysis'){
     steps {
       withSonarQubeEnv('SonarQube') {
          sh "mvn -f pom.xml clean compile sonar:sonar"
       }
     }
   }
    stage('deploy') {
      steps {
        sh 'mvn deploy -DskipTests'
      }
    }
  }
}