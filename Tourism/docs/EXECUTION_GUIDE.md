# Execution Guide

1. Initialize Git: `git init`, `git add .`, `git commit -m "Initial Smart Tourism Portal"`.
2. Push to GitHub: create a repository, run `git remote add origin <repo-url>`, then `git push -u origin main`.
3. Build with Maven: `mvn clean install`, `mvn test`, and `mvn package`.
4. Configure Jenkins: create a Pipeline job, connect the GitHub repository, and use the included `Jenkinsfile`.
5. Build Docker image: `docker build -t smart-tourism-portal:1.0.0 .`.
6. Run Docker container: `docker run -d --name smart-tourism-portal -p 8080:8080 smart-tourism-portal:1.0.0`.
7. Deploy Kubernetes: `kubectl apply -f kubernetes/`, then `kubectl rollout status deployment/smart-tourism-portal -n tourism`.
8. Configure Nagios: copy `monitoring/nagios/smart-tourism.cfg` into the Nagios objects folder and reload Nagios.
9. Start Graphite: `docker compose up -d graphite`.
10. Start Grafana: `docker compose up -d grafana`, then open `http://localhost:3000`.
11. Browser verification: open `http://localhost:8080`, `/actuator/health`, and `/api/status`.
12. Troubleshooting: check `mvn -v`, `java -version`, `docker ps`, `kubectl get pods -n tourism`, and application logs.

## Screenshot Checklist

- Git commit history
- GitHub repository files and Actions build
- Maven successful build output
- Jenkins dashboard and console output
- Docker build output, images, and running container
- Kubernetes pods, services, deployments, and rollout status
- Browser home page, attractions, contact form, and admin dashboard
- Nagios host and service status
- Graphite metrics page
- Grafana dashboard panels
