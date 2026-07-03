# Smart Tourism Information Portal

A complete Java 21 Spring Boot tourism information website with Thymeleaf UI, H2 demo database settings, Docker, Jenkins, Kubernetes, Nagios, Graphite, Grafana, and GitHub Actions assets.

## Features

- Home, about, attractions, hotels, travel guides, gallery, maps, travel tips, booking links, contact form, and admin dashboard
- Responsive Bootstrap 5 UI with dark mode
- Standalone static frontend pages are available in `frontend/`
- Spring MVC controllers, service layer, repository layer, models, actuator health endpoints
- Maven build, Docker image, Jenkins pipeline, Kubernetes manifests, and monitoring configuration

## Architecture

```mermaid
flowchart LR
  User[Browser] --> App[Spring Boot MVC]
  App --> Thymeleaf[Thymeleaf Templates]
  App --> Service[Tourism Service]
  Service --> Repo[In-memory Repository]
  App --> Actuator[Actuator Health and Metrics]
```

```mermaid
flowchart LR
  GitHub --> Jenkins
  Jenkins --> Maven
  Maven --> Docker
  Docker --> Kubernetes
  Kubernetes --> Users
  Kubernetes --> Nagios
  Kubernetes --> Graphite
  Graphite --> Grafana
```

```mermaid
sequenceDiagram
  participant Dev
  participant GitHub
  participant Jenkins
  participant Docker
  participant K8s
  Dev->>GitHub: Push code
  Jenkins->>GitHub: Checkout
  Jenkins->>Jenkins: Maven test and package
  Jenkins->>Docker: Build image
  Jenkins->>K8s: Apply manifests
  K8s-->>Jenkins: Rollout status
```

## Local Setup

Requirements: Java 21, Maven, Git, Docker Desktop, Minikube, kubectl, Jenkins.

```bash
mvn clean install
mvn test
mvn package
java -jar target/smart-tourism-portal-1.0.0.jar
```

Open `http://localhost:8085`.

## Static Frontend

Open `frontend/index.html` directly in a browser to view the standalone HTML, CSS, Bootstrap, and JavaScript frontend pages.

## Docker

```bash
docker build -t smart-tourism-portal:1.0.0 .
docker images
docker run -d --name smart-tourism-portal -p 8085:8085 smart-tourism-portal:1.0.0
docker ps
```

## Kubernetes

```bash
kubectl apply -f kubernetes/
kubectl get pods -n tourism
kubectl get svc -n tourism
kubectl get deployments -n tourism
kubectl describe pod -n tourism
kubectl rollout status deployment/smart-tourism-portal -n tourism
```

## Jenkins

Use the included `Jenkinsfile` to create a CI/CD pipeline.

1. Open Jenkins in the browser at `http://localhost:8080`.
2. Select **New Item**.
3. Enter the job name `Smart-Tourism-Portal-Pipeline`.
4. Select **Pipeline**, then click **OK**.
5. In **General**, add a short description such as `CI/CD pipeline for Smart Tourism Information Portal`.
6. In **Pipeline**, choose **Pipeline script from SCM**.
7. Select **Git** as the SCM.
8. Enter the GitHub repository URL for this project.
9. Set the branch to `*/main`.
10. Set **Script Path** to `Jenkinsfile`.
11. Click **Save**.
12. Click **Build Now** to start the pipeline.

The pipeline performs checkout, Maven clean, Maven test, Maven package, Docker image build, old container removal, Docker container run, Kubernetes deployment, deployment verification, and artifact archiving.

Before running the Jenkins job, confirm that the Jenkins agent has Java 21, Maven, Docker Desktop, kubectl, and access to the project GitHub repository.

## Monitoring

- Nagios: use `monitoring/nagios/smart-tourism.cfg`.
- Graphite: run `docker compose up -d graphite`.
- Grafana: run `docker compose up -d grafana`; Graphite datasource and dashboard are provisioned automatically.

### Localhost Ports

| Tool | Local URL | Port | Purpose |
| --- | --- | --- | --- |
| Jenkins | `http://localhost:8080` | `8080` | CI/CD pipeline dashboard |
| Graphite | `http://localhost:8081` | `8081` | Metrics storage and Graphite web UI |
| Nagios | `http://localhost:8082` | `8082` | Host and service monitoring dashboard |
| Grafana | `http://localhost:3000` | `3000` | Metrics dashboards |
| Smart Tourism App | `http://localhost:8085` | `8085` | Main Spring Boot website |

Use this recommended local setup to avoid port conflicts:

- Jenkins: `8080`
- Graphite: `8081`
- Nagios: `8082`
- Grafana: `3000`
- Spring Boot application: `8085`

Example Docker Compose monitoring commands:

```bash
docker compose up -d graphite
docker compose up -d grafana
```

Then open Grafana at `http://localhost:3000` and verify that the Graphite datasource is available.

## Git And GitHub

```bash
git init
git add .
git commit -m "Initial Smart Tourism Portal"
git branch -M main
git remote add origin <repository-url>
git push -u origin main
```

## Troubleshooting

- Maven build fails: verify `java -version` shows Java 21 and run `mvn -U clean verify`.
- Docker cannot find jar: run `mvn package` before `docker build`.
- Kubernetes image pull fails in Minikube: run `minikube image load smart-tourism-portal:1.0.0`.
- App health: check `http://localhost:8085/actuator/health`.

## Future Scope

Add authentication, persistent database storage, payment gateway integration, real map API keys, centralized logging, and production-grade observability.
