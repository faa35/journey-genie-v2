Certainly! Below is a well-structured `README.md` file summarizing the steps you took, the issues encountered, and the results achieved. This README provides a clear overview of your work for others who might want to replicate or understand your process.

---

# JourneyGenie Deployment on Azure

This repository documents the deployment of **JourneyGenie**, a Java-based application, on Azure App Service using Azure CLI. The process includes creating a resource group, an App Service Plan, and deploying the application with a specific runtime configuration.

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Steps Taken](#steps-taken)
3. [Issues Encountered](#issues-encountered)
4. [Final Result](#final-result)
5. [Next Steps](#next-steps)

---

## Prerequisites

Before following this guide, ensure you have the following:
- An active Azure subscription.
- Azure CLI installed and configured (`az login`).
- Docker installed (optional, if containerization is required).

---

## Steps Taken

### 1. **Azure Login**
   - Logged into Azure CLI using `az login`.
   - Selected the appropriate tenant and subscription:
     - Tenant: `Default Directory`
     - Subscription: `Azure subscription 1 (6b283204-740d-4862-8dcc-952db01580de)`.

### 2. **Resource Group Creation**
   - Created a resource group named `JourneyGenieResourceGroup` in the `eastus` region:
     ```bash
     az group create --name JourneyGenieResourceGroup --location eastus
     ```

### 3. **App Service Plan Creation**
   - Attempted to create an App Service Plan in `eastus`, but encountered a quota issue:
     ```
     This region has quota of 0 instances for your subscription. Try selecting different region or SKU.
     ```
   - Successfully created the App Service Plan in the `canadacentral` region:
     ```bash
     az appservice plan create --name JourneyGenieAppServicePlan \
       --resource-group JourneyGenieResourceGroup \
       --sku F1 \
       --is-linux \
       --location canadacentral
     ```

### 4. **Web App Creation**
   - Listed available runtimes for Linux using:
     ```bash
     az webapp list-runtimes --linux | Select-String "JAVA"
     ```
   - Identified the runtime `JAVA:17-java17` for Java 17.
   - Created the web app `JourneyGenie` with the specified runtime:
     ```bash
     az webapp create --name JourneyGenie \
       --resource-group JourneyGenieResourceGroup \
       --plan JourneyGenieAppServicePlan \
       --runtime "JAVA:17-java17"
     ```

### 5. **Verification**
   - Verified the web app's configuration:
     ```bash
     az webapp show --name JourneyGenie --resource-group JourneyGenieResourceGroup
     ```
   - Confirmed that the app is running with the correct runtime (`JAVA|17-java17`) and is accessible at:
     ```
     https://journeygenie.azurewebsites.net
     ```

---

## Issues Encountered

1. **Region Quota Issue**:
   - Initially attempted to create the App Service Plan in `eastus`, but encountered a quota limitation:
     ```
     This region has quota of 0 instances for your subscription.
     ```
   - Resolved by switching to the `canadacentral` region.

2. **Incorrect Runtime Format**:
   - Used an incorrect runtime format (`JAVA|17-java17`) initially, which caused errors.
   - Corrected the format to `JAVA:17-java17` after consulting the runtime list.

3. **Docker Command Not Found**:
   - Attempted to build a Docker image locally but encountered the error:
     ```
     docker : The term 'docker' is not recognized as the name of a cmdlet.
     ```
   - This indicates Docker is not installed on the local machine. If containerization is required, Docker must be installed.

---

## Final Result

- Successfully deployed **JourneyGenie** on Azure App Service.
- The application is hosted in the `canadacentral` region under the resource group `JourneyGenieResourceGroup`.
- The web app is accessible at:
  ```
  https://journeygenie.azurewebsites.net
  ```
- The runtime environment is configured to use **Java 17** (`JAVA|17-java17`).

---

## Next Steps

1. **Containerization**:
   - Install Docker and containerize the application for better portability.
   - Push the Docker image to Azure Container Registry (ACR) and deploy it to Azure Kubernetes Service (AKS) if needed.

2. **SSL Configuration**:
   - Configure SSL certificates for secure communication:
     ```bash
     az webapp config ssl bind --resource-group JourneyGenieResourceGroup \
       --name JourneyGenie --certificate-thumbprint <thumbprint> \
       --ssl-type SNI
     ```

3. **CI/CD Pipeline**:
   - Set up a CI/CD pipeline using GitHub Actions or Azure DevOps for automated deployments.

4. **Monitoring and Logging**:
   - Enable Azure Monitor and Application Insights for better observability:
     ```bash
     az monitor app-insights component create --app JourneyGenie \
       --location canadacentral --resource-group JourneyGenieResourceGroup
     ```

---

## References

- [Azure CLI Documentation](https://learn.microsoft.com/en-us/cli/azure/)
- [Azure App Service Documentation](https://learn.microsoft.com/en-us/azure/app-service/)
- [Java on Azure](https://learn.microsoft.com/en-us/azure/developer/java/)

---

Feel free to customize this README further based on additional details or future updates to your project!