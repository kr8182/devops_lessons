def call(body) {
    def config= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()


    pipeline {
        agent any
        options {
            buildDiscarder(logRotator(numToKeepStr: '2'))
            disableConcurrentBuilds()
            timestamps()
        }

        tools {
            jdk 'JDK17'
            maven 'maven_3.9.5'
        }

        environment {
            GITHUB_REPO_CRED = credentials("48b7c3fd-2318-489c-a476-731fa2969db4")
            GITHUB_REPO_OWNER = "levelup-devops"
            GITHUB_REPO_NAME = "kr8182"
            GITHUB_REPO_URL = "https://github.com/${GITHUB_REPO_OWNER}/${GITHUB_REPO_NAME}.git"
            APPS_LIST_FILE = "lesson13/apps/apps.json"
        }

        stages {
            stage('Checkout') {
                steps {
                    git branch: 'main', changelog: false, credentialsId: '48b7c3fd-2318-489c-a476-731fa2969db4', poll: false, url: config.GITHUB_REPO_URL
                }
            }
            stage('Prepare') {
                steps {
                    script {
                        func = load 'lesson13/func.groovy'
                        Integer parallel_executors = 2
                        def apps = readJSON file: config.APPS_LIST_FILE
                        buildStages = stagePrepare(apps, "build", parallel_executors)
                        uploadStages = stagePrepare(apps, "upload", parallel_executors)
                    }
                }
            }

            stage('Build') {
                steps {
                    script {
                        buildStages.each { build ->
                            parallel build
                        }
                    }
                }
            }

            stage('Upload') {
                steps {
                    script {
                        uploadStages.each { upload ->
                            parallel upload
                        }
                    }
                }
            }
        }
    }
}