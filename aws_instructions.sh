#!/usr/bin/env bash

# Create bucket in s3
aws s3 mb s3://ia.ws

# Copy AWSExperiment.jar into previously created ia.ws s3 bucket
aws s3 cp AWSExperiment.jar s3://ia.ws

# Create an IAM role to access s3 from ec2
aws iam create-role --role-name s3fullaccess --assume-role-policy-document file://s3fullaccesspolicy.json

# Find AmazonS3FullAccess arn
aws iam list-policies | grep AmazonS3FullAccess

# Attach AmazonS3FullAccess policy to role
aws iam attach-role-policy --role-name s3fullaccess --policy-arn arn:aws:iam::aws:policy/AmazonS3FullAccess

# Create key pair
aws ec2 create-key-pair --key-name IAKeyPair --query 'KeyMaterial' --output text > MyKeyPair.pem

# Create security group
aws ec2 create-security-group --group-name MySG --description "A security group for ssh and tcp connection"

# Adding a Rule for Inbound SSH Traffic to a Linux Instance
aws ec2 authorize-security-group-ingress --group-id sg-0adaf703bd676fae8 --protocol tcp --port 22 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id sg-0adaf703bd676fae8 --protocol tcp --port 8080 --cidr 0.0.0.0/0

# Run Amazon Linux AMI 2018.03.0 (HVM), SSD Volume Type instance
aws ec2 run-instances --image-id ami-0080e4c5bc078760e --count 1 --instance-type t2.micro --key-name MyKeyPair --associate-public-ip-address --security-group-ids sg-XXX

# Update credentials of pem file
chmod 600 MyKeyPair.pem
# scp
scp -i MyKeyPair.pem aa.json ec2-user@ec2-52-91-68-41.compute-1.amazonaws.com:/home/ec2-user

# ssh
ssh -i MyKeyPair.pem ec2-user@ec2-52-91-68-41.compute-1.amazonaws.com

# Query EC2 Instances
aws ec2 describe-instances --query 'Reservations[*].Instances[*].[InstanceId,ImageId,State.Name,Placement.AvailabilityZone,Instances.InstanceType]'