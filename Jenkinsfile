slack = new Slack(this).startThread()
extendedSteps = new ExtendedSteps(this)

pipeline {
    agent any

    stages {
        stage('Lint') {
            steps {
                script {
                    extendedSteps """
                        ./gradlew ktlint
                    """
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    extendedSteps """
                        ./gradlew clean buildDebugPreBundle
                    """
                }
            }
        }
        stage('Tests') {
            steps {
                script {
                    extendedSteps """
                        ./gradlew testDebugUnitTest
                    """
                }
            }
        }
    }

    post {
        success {
            script {
                slack.markThreadAsGreen()
            }
        }
        failure {
            script {
                slack.markThreadAsRed()
            }
        }
        always {
            junit "**/build/test-results/**/*.xml"
        }
    }
}