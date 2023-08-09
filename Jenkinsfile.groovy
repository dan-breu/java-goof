

try {
    node {
        stage('Preparation') {

        }

        stage('Snyk Open Source Scan - SCA') {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
        snykSecurity additionalArguments: '-debug', failOnIssues: false, failOnError: false, monitorProjectOnBuild: true, organisation: 'pichincha-pe',
                                                                projectName: '${JOB_NAME}', severity: 'high', snykInstallation: 'snyk@latest',
                                                                snykTokenId: 'organisation-snyk-api-token'

          }

      }


        stage('Snyk Code Scan - SAST') {
        snykSecurity additionalArguments: '--code -debug', failOnIssues: false, failOnError: false, monitorProjectOnBuild: true, organisation: 'pichincha-pe',
                                                        projectName: '${JOB_NAME}', snykInstallation: 'snyk@latest',
                                                        snykTokenId: 'organisation-snyk-api-token'

    }

        stage('Build') {
            echo 'Copied enviroment file'
        }

        stage("Quality Gate"){
            echo 'OK'
        }

        stage('Snyk Container Scan') {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                snykSecurity additionalArguments: '--container container_image -d', failOnIssues: false, 
                             organisation: 'ORGANIZATION_NAME', projectName: '${JOB_NAME}', 
                             severity: 'high', snykInstallation: 'snyk@latest', snykTokenId: 'organisation-snyk-api-token'
            }
        }

        stage('Snyk IaC Scan') {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                snykSecurity additionalArguments: '--iac --report -d', failOnIssues: false, 
                             organisation: 'ORGANIZATION_NAME', projectName: '${JOB_NAME}', 
                             severity: 'high', snykInstallation: 'snyk@latest', snykTokenId: 'organisation-snyk-api-token'
            }
        }

        stage('Delivery app  to enviroment') {

              }
            
        }
        stage('Post Execution') {

        }
    
} catch (Exception e) {
    node {
        throw e
    }
}
