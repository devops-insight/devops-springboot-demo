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
        sh (script: 'chmod u+x gradlew')
        sh (script: './gradlew build')
      }
    }
  }
}