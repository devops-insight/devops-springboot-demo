pipeline {

  environment {
    repoName = 'fincrime/devops-springboot-demo'
    repoCredentialsId = 'dockerhub-token'
    dockerImage = ''
  }

  agent any
  stages {
    stage('Verify Branch') {
      steps {
        echo "$GIT_BRANCH"
      }
    }

    stage('Build: application') {
      steps {
        sh (script: '''
           gradle clean build
        ''')
      }
    }

    
  }
}

