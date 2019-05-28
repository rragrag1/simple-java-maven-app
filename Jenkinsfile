pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        
        stage ('Build') {
            steps {
                echo 'Building will stats ... '
                // withMaven(maven: 'maven') {
                    sh 'mvn  -DskipTests clean package'
                // }
            }
        }
        stage('Test') {
            steps {
                echo 'Test will stats ...  '
                // withMaven(maven: 'maven') {
                    sh 'mvn test'
                // }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Hello World Deploy'
            }
        }
     stage('Parallel In Sequential') {
                    parallel {
                        stage('In Parallel 1') {
                            steps {
                                echo "In Parallel 1"
                            }
                        }
                        stage('In Parallel 2') {
                            steps {
                                echo "In Parallel 2"
                            }
                        }
                    }
                }
       
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}
