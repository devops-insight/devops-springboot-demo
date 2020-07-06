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

    stage ('Publish to Artifactory'){
      steps {
        script {
          def server = Artifactory.server "artifactory"
          def buildInfo = Artifactory.newBuildInfo()
          rtGradle = Artifactory.newGradleBuild()
          rtGradle.deployer server: server, releaseRepo: 'fcp-webapp-release-local', snapshotRepo: 'fcp-webapp-snap-local'
          rtGradle.deployer.artifactDeploymentPatterns.addInclude("devops-springboot-demo*")

          rtGradle.tool = 'gradle'
          buildInfo = rtGradle.run  buildFile: 'build.gradle', tasks: 'build artifactoryPublish'

          buildInfo.env.capture = true
          buildInfo.name = 'devops-springboot-demo'
          server.publishBuildInfo buildInfo
        }
      }
    }
  }
}

