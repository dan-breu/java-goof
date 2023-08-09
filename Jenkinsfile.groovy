dir('subDir') {
    checkout scm
}

pipeline {
    agent any

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
