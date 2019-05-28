pipeline {
    agent any
    stages {
        
        stage ('Build') {
            steps {
                withMaven(maven: 'maven_3_5_0') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Hello World Test '
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
