

pipeline {
    agent any


    stages {
        
        stage('git clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/dan-breu/java-goof'
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
            }
        }

         stage('Snyk SCA') {
            steps {
                snykSecurity(
                    snykInstallation: 'snyk@latest',
                    snykTokenId: 'snyk-api-token',
                    failOnIssues: false,
                    monitorProjectOnBuild: true,
                    additionalArguments: '--all-projects --d'
                    
                )
            }
        }


        
    }
} 
