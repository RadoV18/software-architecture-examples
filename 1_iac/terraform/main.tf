terraform {
    required_version = ">=1.9.0"
    required_providers {
        aws = {
            source = "hashicorp/aws"
            version = "~> 4.16"
        }
    }
}

provider "aws" {
    region = "us-east-1"
}

resource "aws_key_pair" "ssh_key" {
    key_name   = "radov@radomir"
    public_key = file("~/.ssh/id_rsa.pub")
}

resource "aws_security_group" "main" {
    name        = "main"
    description = "Allow inbound traffic over SSH"

    ingress {
        from_port   = 22
        to_port     = 22
        protocol    = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }
}

resource "aws_instance" "hello_ec2" {
    ami = "ami-0e86e20dae9224db8"
    instance_type = "t2.micro"
    associate_public_ip_address = true
    key_name = aws_key_pair.ssh_key.key_name
    vpc_security_group_ids = [aws_security_group.main.id]
}
