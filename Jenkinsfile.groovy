

pipeline {
    agent any

    dir('subDir') {
    checkout scm
}

    stages {
        
        stage('git clone') {
            steps {
                git url: 'https://github.com/dan-breu/java-goof'
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
            }
        }

         stage('Snyk SCA') {
            steps {
                echo 'Building..'
            }
        }


        
    }
} 
