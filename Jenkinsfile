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

    stage('Build: Gradle') {
      steps {
        sh (script: '''
          sudo docker images
          sudo docker build -t devops-springboot-demo:1.0.0 .
          sudo docker images
        ''')
      }
    }
  }
}