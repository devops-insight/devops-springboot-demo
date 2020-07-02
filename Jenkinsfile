pipeline {
  agent any
  stages {
    stage('Verify Branch') {
      steps {
        echo "$GIT_BRANCH"
      }
    }

    stage('Build: Gradle') {
      steps {
        sh (script: './gradlew build')
      }
    }
  }
}