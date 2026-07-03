pipeline {
    agent any

    environment {
        IMAGE_NAME = 'smart-tourism-portal'
        IMAGE_TAG = '1.0.0'
        CONTAINER_NAME = 'smart-tourism-portal'
        APP_PORT = '8085'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Java Version') {
            steps {
                bat 'java -version'
            }
        }

        stage('Verify Maven Version') {
            steps {
                bat 'mvn -version'
            }
        }

        stage('Maven Clean') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Maven Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Maven Package') {
            steps {
                bat 'mvn package'
            }
        }

        stage('Archive JAR') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, onlyIfSuccessful: true
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME%:%IMAGE_TAG% .'
            }
        }

        stage('Remove Existing Container') {
            steps {
                bat 'docker rm -f %CONTAINER_NAME% || exit /b 0'
            }
        }

        stage('Run Docker Container') {
            steps {
                bat 'docker run -d --name %CONTAINER_NAME% -p %APP_PORT%:%APP_PORT% %IMAGE_NAME%:%IMAGE_TAG%'
            }
        }

        stage('Deploy to Kubernetes (if Minikube is running)') {
            steps {
                script {
                    def kubectlExists = bat(script: 'where kubectl >nul 2>nul', returnStatus: true) == 0
                    def minikubeExists = bat(script: 'where minikube >nul 2>nul', returnStatus: true) == 0

                    if (kubectlExists && minikubeExists) {
                        def minikubeRunning = bat(script: 'minikube status | findstr /I "Running" >nul 2>nul', returnStatus: true) == 0
                        if (minikubeRunning) {
                            bat 'kubectl apply -f kubernetes/'
                        } else {
                            echo 'Minikube is installed but not running. Skipping Kubernetes deployment.'
                        }
                    } else {
                        echo 'kubectl or minikube was not found. Skipping Kubernetes deployment.'
                    }
                }
            }
        }

        stage('Verify Pods') {
            steps {
                script {
                    def kubectlExists = bat(script: 'where kubectl >nul 2>nul', returnStatus: true) == 0
                    if (kubectlExists) {
                        bat 'kubectl get pods -n tourism || kubectl get pods'
                    } else {
                        echo 'kubectl was not found. Skipping pod verification.'
                    }
                }
            }
        }

        stage('Verify Services') {
            steps {
                script {
                    def kubectlExists = bat(script: 'where kubectl >nul 2>nul', returnStatus: true) == 0
                    if (kubectlExists) {
                        bat 'kubectl get svc -n tourism || kubectl get svc'
                    } else {
                        echo 'kubectl was not found. Skipping service verification.'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Post Build Success: Smart Tourism Portal CI/CD completed successfully.'
            echo 'Application URL: http://localhost:8085'
        }
        failure {
            echo 'Post Build Failure: Smart Tourism Portal CI/CD failed. Check Java, Maven, Docker, and Kubernetes logs.'
        }
        always {
            echo 'Build finished.'
        }
    }
}
