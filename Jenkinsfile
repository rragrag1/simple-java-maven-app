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
