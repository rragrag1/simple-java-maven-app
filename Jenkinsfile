pipeline {
    agent any
    stages {
        stage('Dev') {
            steps {
                echo 'Hello World Dev'
            }
        }
        stage('Test') {
            steps {
                echo 'Hello World Test '
            }
        }
        stage('Prod') {
            steps {
                echo 'Hello World Prod'
            }
        }
       
    }
    post { 
        always { 
            echo 'I will always say Hello again!'
        }
    }
}
