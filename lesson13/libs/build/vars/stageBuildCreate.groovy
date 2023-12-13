def call(app, value, lock_id) {
    return {
        stage(app) {
            lock("Build-Lock-${lock_id}") {
                dir("lesson13/apps/${app}") {
                    sh "mvn -B -DskipTests -Dmaven.repo.local=${WORKSPACE}/.m2/repository clean package"
                }
            }
        }
    }
}