#!/bin/bash
kubectl create -f ./mongo-pv.yaml
kubectl create -f ./mongo-pvc.yaml
kubectl create -f ./mongo-deployment.yaml
kubectl create -f ./app-service.yaml
kubectl create -f ./app-deployment.yaml
kubectl get services
minikube ip
