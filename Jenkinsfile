pipeline {
    agent {label "slave1"}

    stages {
        stage('Build') {
            steps {
                script {

                    echo 'Building..'
                    sh "ls -lha"
                    sh "sudo docker build -t angular-front:lts ." 
                    sh "sudo docker images" 
                    sh "sudo docker run -d -ti --name angular-front -p 80:80 angular-front:lts"

                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}