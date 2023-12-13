def stagePrepare(apps, action, parallel_executors) {
    buildStageList = []
    buildParallelMap = [:]
    apps.eachWithIndex { app, value, i ->
        Integer lock_id = i % parallel_executors
        println lock_id
        if (action == "build") {
            buildParallelMap.put(app, stageBuildCreate(app, value, lock_id))
        }
        if (action == "upload") {
            buildParallelMap.put(app, stageUploadCreate(app, value, lock_id))
        }
    }
    buildStageList.add(buildParallelMap)
    return buildStageList
}

def stageBuildCreate(app, value, lock_id) {
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

def stageUploadCreate(app, value, lock_id) {
    return {
        stage(app) {
            lock("Upload-Lock-${lock_id}") {
                dir("lesson13/apps/${app}") {
                    sh "mvn -DskipTests -s settings.xml -Dmaven.repo.local=${WORKSPACE}/.m2/repository deploy"
                }
            }
        }
    }
}