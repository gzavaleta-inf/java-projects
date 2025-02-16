pipeline {
  agent any
  parameters {
      string(name:'name_container', defaultValue:'container-persona-dev', description:'Nombre del contenedor')
      string(name:'name_imagen', defaultValue:'persona-dev', description:'Nombre de la imagen')
      string(name:'tag_imagen', defaultValue:'latest', description:'Etiqueta de la imagen')
      string(name:'puerto_imagen', defaultValue:'8090', description:'Puerto a publicar')
  }
  environment {
      name_final = "${name_container}${tag_imagen}${puerto_imagen}"
  }
  stages {
    stage('stop/rm') {
      when {
        expression {
            DOCKER_EXIST = sh(returnStdout: true, script: 'echo "$(docker ps -q --filter name:${name_final})"')
            return DOCKER_EXIST != ''
        }
      }
      steps {
        script {
            sh '''
              docker stop ${name_final}
            '''
        }
      }
    }
    stage('build') {
      steps {
        script {
          sh '''
            docker build . -t ${name_imagen}:${tag_imagen}
          '''
        }
      }
      stage('run'){
        steps {
          script {
            sh '''
              docker run -dp ${puerto_imagen}:8091 --name ${name_final} ${name_imagen}:${tag_imagen}
            '''
          }
        }
      }
    }
  }
}