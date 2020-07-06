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

    stage ('Publish to Artifactory | Gradle'){
      steps {
        script {
          def server = Artifactory.server "artifactory"
          def buildInfo = Artifactory.newBuildInfo()
          rtGradle = Artifactory.newGradleBuild()
          rtGradle.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
          rtGradle.deployer server: server, releaseRepo: 'gradle-dev-local', snapshotRepo: 'gradle-release-local'
          //rtGradle.deployer.artifactDeploymentPatterns.addInclude("devops-springboot-demo*")

          rtGradle.tool = 'gradle'
          buildInfo = rtGradle.run  buildFile: 'build.gradle', tasks: 'build artifactoryPublish'

          buildInfo.env.capture = true
          buildInfo.name = 'devops-springboot-demo'
          server.publishBuildInfo buildInfo
        }
      }
    }

       stage ('Publish to Artifactory | Maven'){
              steps {
                script {
                  def server = Artifactory.server "artifactory"
                  def buildInfo = Artifactory.newBuildInfo()
                  def rtMaven = Artifactory.newMavenBuild()
                  rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                  rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                 // rtMaven.deployer.artifactDeploymentPatterns.addInclude("devops-springboot-demo*")
                  rtMaven.deployer.deployArtifacts (true)
                  rtMaven.tool = 'maven'
                  buildInfo = rtMaven.run pom: 'pom.xml', goals: "clean install -Dlicense.skip=true"
                  buildInfo.env.capture = true
                  buildInfo.name = 'devops-springboot-demo'
                  server.publishBuildInfo buildInfo
                }
              }
            }
  }
}

