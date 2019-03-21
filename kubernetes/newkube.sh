#!/bin/bash
kubectl create -f ./mongo-volume.yaml
kubectl create -f ./mongo-volume-claim.yaml
kubectl create -f ./mongo-deployment.yaml
kubectl create -f ./app-service.yaml
kubectl create -f ./app-deployment.yaml
kubectl get services
minikube ip
