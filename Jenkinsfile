pipeline {
    agent any
    tools {
        maven 'maven'
    }
    
    environment {
        // This can be nexus3 or nexus2
        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "localhost:8081"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "repository-example"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "nexus-credentials"
    }
    
    stages {
        
        stage ('Build') {
            steps {
                echo 'Building will stats 2... '
                withMaven(maven: 'maven') {
                    sh 'mvn  -DskipTests clean package'
                 }
            }
        }
        stage('Test') {
            steps {
                echo 'Test will stats from eclipse ...  '
                 withMaven(maven: 'maven') {
                    sh 'mvn test'
                 }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Coverage with JaCoCo') {
            steps {
                echo 'Code Coverage'
                jacoco(
                    execPattern: 'target/coverage-reports/*.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java',
                    exclusionPattern: 'src/test*',
                    // execPattern: './target/code-coverage/**.exec',
                    // classPattern: './target/classes',
                    // sourcePattern: 'src',
                    inclusionPattern: 'com/mycompany/**',
                    changeBuildStatus: true,
                    minimumInstructionCoverage: '60',
                    // minimumBranchCoverage: '40',
                    // minimumComplexityCoverage: '40',
                    // minimumLineCoverage: '60',
                    // minimumMethodCoverage: '50',
                    // minimumClassCoverage: '60',
                     maximumInstructionCoverage: '70',
                    // maximumBranchCoverage: '49',
                    // maximumComplexityCoverage: '49',
                    // maximumLineCoverage: '70',
                    // maximumMethodCoverage: '59',
                    // maximumClassCoverage: '90'

                )
            }
            post{
                failure {
                    script{
                        error "Failed, exiting now..."
                    }
                }
                // unstable {
                //     script{
                //         // error "Unstable, exiting now..."                    
                //      }
                // }
            }
        }

        // stage('Sonar') {
        //     steps {
        //         echo 'Sonar Scanner'
        //         script {
        //             scannerHome = tool 'SonarQube Scanner'
        //         }
        //         withSonarQubeEnv('SonarQube') {
        //             sh "${scannerHome}/bin/sonar-scanner"
        //             //sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=advant-web -Dsonar.sources=. -Dsonar.exclusions=node_modules/**,build/** -Dsonar.projectVersion=1.0.${BUILD_NUMBER}"
        //         }
               
        //     }
        // // }
        stage('Sonar with maven') {
            steps {
                echo 'Sonar with maven'
                withSonarQubeEnv('SonarQube') {
                    withMaven(maven: 'maven') {
                        sh "mvn sonar:sonar"
                    }
                }
               
            }
        }
        // stage("Quality Gate") {
        //     steps {
        //         timeout(time: 1, unit: 'HOURS') {
        //             // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
        //             // true = set pipeline to UNSTABLE, false = don't
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }

        stage("Quality Gate"){
          steps {
            timeout(time: 1, unit: 'HOURS') {
                script {
                    def qg = waitForQualityGate()
                    echo "Sonar Quality Gate status: ${qg.status}"
                    if (qg.status != 'OK') {
                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                }
            }
          }
      }
        stage("publish to nexus") {
            steps {
                script {
                    // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                    pom = readMavenPom file: "pom.xml";
                    // Find built artifact under target folder
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    // Print some info from the artifact found
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extract the path from the File found
                    artifactPath = filesByGlob[0].path;
                    // Assign to a boolean response verifying If the artifact name exists
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                // Artifact generated such as .jar, .ear and .war files.
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                // Lets upload the pom.xml file for additional information for Transitive dependencies
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
        
//     stage('Parallel In Sequential') {
//                    parallel {
//                        stage('In Parallel 1') {
//                            steps {
//                                echo "In Parallel 1"
//                            }
//                        }
//                        stage('In Parallel 2') {
//                            steps {
//                                echo "In Parallel 2"
//                            }
//                        }
//                    }
//                }
//       
//    }
//    post { 
//        always { 
//            echo 'I will always say Hello again!'
//        }
    }
}
