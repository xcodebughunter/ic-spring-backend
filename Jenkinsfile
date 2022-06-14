pipeline {
    agent {label "slave1"}

    stages {
        stage('Build') {
            steps {
                script {

                    echo 'Building..'
                    sh "ls -lha"
                    sh "sudo docker build -t spring-bck:lts ." 
                    sh "sudo docker images" 
                    sh "sudo docker run -d -ti --name spring-bck -p 8080:8080 spring-bck:lts"

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