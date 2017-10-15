job('seed-project-build'){
    scm {
        git('https://github.com/rajattur/node-wedserver.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@newtech.academy')
        }
    }

    triggers {
        scm('H/5 * * * *')
    }

    wrappers {
        nodejs('nodejs')
    }

    steps {
        dockerBuildAndPublish {
            repositoryName('rajattur/seed-nodejs-project')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }

}