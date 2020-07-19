pipeline {
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

    stage ('Publish to Artifactory | Maven'){
      steps {
        script {
          def server = Artifactory.server "artifactory"
          def buildInfo = Artifactory.newBuildInfo()
          def rtMaven = Artifactory.newMavenBuild()
         // rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
          rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
          rtMaven.tool = 'maven'
          buildInfo = rtMaven.run pom: 'pom.xml', goals: "install -Dlicense.skip=true"
          buildInfo.env.capture = true
          buildInfo.name = 'devops-springboot-demo'
          server.publishBuildInfo buildInfo
        }
      }
    }
  }
}

