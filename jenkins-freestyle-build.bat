@echo off
setlocal enabledelayedexpansion

set IMAGE_NAME=smart-tourism-portal
set IMAGE_TAG=1.0.0
set CONTAINER_NAME=smart-tourism-portal
set APP_PORT=8085

echo =====================================================
echo Checkout
echo Jenkins Freestyle performs checkout from Source Code Management.
echo =====================================================

echo =====================================================
echo Verify Java Version
echo =====================================================
java -version
if errorlevel 1 exit /b 1

echo =====================================================
echo Verify Maven Version
echo =====================================================
mvn -version
if errorlevel 1 exit /b 1

echo =====================================================
echo Maven Clean
echo =====================================================
mvn clean
if errorlevel 1 exit /b 1

echo =====================================================
echo Maven Test
echo =====================================================
mvn test
if errorlevel 1 exit /b 1

echo =====================================================
echo Maven Package
echo =====================================================
mvn package
if errorlevel 1 exit /b 1

echo =====================================================
echo Archive JAR
echo Configure Jenkins Post-build Action: Archive the artifacts target/*.jar
echo =====================================================
dir target\*.jar
if errorlevel 1 exit /b 1

echo =====================================================
echo Build Docker Image
echo =====================================================
docker build -t %IMAGE_NAME%:%IMAGE_TAG% .
if errorlevel 1 exit /b 1

echo =====================================================
echo Remove Existing Container
echo =====================================================
docker rm -f %CONTAINER_NAME%
echo Existing container removal completed or container was not present.

echo =====================================================
echo Run Docker Container
echo Jenkins uses localhost:8080, so the app runs on localhost:%APP_PORT%.
echo =====================================================
docker run -d --name %CONTAINER_NAME% -p %APP_PORT%:%APP_PORT% %IMAGE_NAME%:%IMAGE_TAG%
if errorlevel 1 exit /b 1

echo =====================================================
echo Deploy to Kubernetes if Minikube is running
echo =====================================================
where kubectl >nul 2>nul
if errorlevel 1 (
  echo kubectl not found. Skipping Kubernetes deployment.
) else (
  where minikube >nul 2>nul
  if errorlevel 1 (
    echo minikube not found. Skipping Kubernetes deployment.
  ) else (
    minikube status | findstr /I "Running" >nul 2>nul
    if errorlevel 1 (
      echo Minikube is not running. Skipping Kubernetes deployment.
    ) else (
      kubectl apply -f kubernetes/
      if errorlevel 1 exit /b 1
    )
  )
)

echo =====================================================
echo Verify Pods
echo =====================================================
where kubectl >nul 2>nul
if not errorlevel 1 (
  kubectl get pods -n tourism
  if errorlevel 1 kubectl get pods
)

echo =====================================================
echo Verify Services
echo =====================================================
where kubectl >nul 2>nul
if not errorlevel 1 (
  kubectl get svc -n tourism
  if errorlevel 1 kubectl get svc
)

echo =====================================================
echo Post Build Success
echo Smart Tourism Portal build completed successfully.
echo Application URL: http://localhost:%APP_PORT%
echo =====================================================

endlocal
