# Infrastructure as Code

## Table of Contents
1. [Introduction](#introduction)
1. [What is Infrastructure as Code?](#what-is-infrastructure-as-code)
1. [Why Infrastructure as Code?](#why-infrastructure-as-code)
1. [Prerequisites](#prerequisites)
1. [Examples](#examples-of-infrastructure-as-code)
    - [Local Environment](#local-environment)
        - [Using Docker Compose](#using-docker-compose)
        - [Using Vagrant](#using-vagrant)
    - [Cloud Environment](#cloud-environment)
        - [Using Terraform](#using-terraform)

## Introduction {#introduction}

## What is Infrastructure as Code? {#what-is-infrastructure-as-code}

Infrastructure as Code is a practice in which infrastructure can be defined using code. This allows to automate the process of provisioning infrastructure, making it more efficient, reliable and scalable.
Infrastructure as Code is a key component of DevOps, as it allows to treat infrastructure as software, resulting in reduced manual work and increased consistency.

## Why Infrastructure as Code? {#why-infrastructure-as-code}

Infrastructure as Code comes in handy when you need to create, update, or delete infrastructure resources.

## Prerequisites {#prerequisites}

In order to follow the examples in this repository, you will need to have the following tools installed on your machine:

- [Docker](https://docs.docker.com/get-started/get-docker/)
- [Vagrant](https://developer.hashicorp.com/vagrant/docs/installation)
- [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
- [Terraform](https://developer.hashicorp.com/terraform/tutorials/aws-get-started/install-cli)
- [AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

Additionally, you will need to have an AWS account. If you don't have one, you can create one [here](https://aws.amazon.com/free). You can use the free tier to follow the examples in this repository.

## Examples {#examples-of-infrastructure-as-code}

### Local Environment {#local-environment}

#### Using Docker Compose {#using-docker-compose}

Docker compose is a tool that allows you to define and run multi-container Docker applications using a single YAML file. In this example, we will use Docker Compose to define a simple web application that consists of an Nginx web server, a Spring Boot application, and a PostgreSQL database.

#### Using Vagrant {#using-vagrant}

Vagrant is a tool that allows you to create and configure environments in a consistent and repeatable way. For this, Vagrant uses a hypervisor such as VirtualBox to create virtual machines. In this example, we will use Vagrant to create a virtual machine running Ubuntu 22.04 and install g++ and Visual Studio Code.

To execute the example, go to the `ubuntu_22.04` directory and run the following command:

```bash
vagrant up
```

This will create a virtual machine running Ubuntu 22.04 and install g++, Visual Studio Code and Lubuntu desktop environment. You can access the virtual machine over SSH or using the VirtualBox GUI.

```bash
vagrant ssh
```

### Cloud Environment {#cloud-environment}

#### Using Terraform {#using-terraform}

Terraform is a tool for building, changing, and versioning infrastructure safely and efficiently. Terraform uses a declarative configuration language to describe the desired state of your infrastructure. In this example, we will use a Terraform configuration file to create an EC2 instance on AWS.

