# Gamify

![Build and publish Docker image](https://github.com/heig-AMT/gamify/workflows/Build%20and%20publish%20Docker%20image/badge.svg?branch=dev)
[![Heroku App Status](http://heroku-shields.herokuapp.com/heig-amt-gamify)](https://heig-amt-gamify.herokuapp.com)
![Run tests](https://github.com/heig-AMT/gamify/workflows/Run%20tests/badge.svg?branch=dev)

This repository contains our version of the second project of the AMT class of HEIG-VD.

**:seedling: Our Cucumber reports for the `dev` branch are on [GitHub Pages](https://heig-amt.github.io/gamify/) :seedling:**

## Structure

+ The `docker` folder contains deployment information for our images and environments.
+ The `full-project` folder is useful to open both the implementation and specs simulateneously in IntelliJ IDEA.
+ The `gamify-impl` contains a Spring Boot project that will gamify your next app. That's probably what you came here for :v:
+ The `gamify-specs` contains our code-based specification. It is used to validate the behavior of the API.

## Team

| Name                                   |                                  |
|----------------------------------------|----------------------------------|
| Matthieu Burguburu 					 | matthieu.burguburu@heig-vd.ch    |
| David Dupraz                           | david.dupraz@heig-vd.ch          |
| Alexandre Piveteau 				     | alexandre.piveteau@heig-vd.ch    |
| Guy-Laurent Subri                      | guy-laurent.subri@heig-vd.ch     |

## Running the service (locally)

Assuming you have Docker installed locally, you can run the following scripts to get the app running on your `8080` port :

```bash
sh ./build-image.sh
sh ./run-compose.sh
```

The Swagger docs will then be available on the [root endpoint](http://localhost:8080/).

## Verifying the service with Cucumber

You can run the Cucumber validation via Maven. You need to make sure the app is running on your `8080` port :

```
cd gamify-specs/
mvn clean test
```

## Deployments

A live version of our API is available on [Heroku](https://heig-amt-gamify.herokuapp.com). We're using a free plan, so it may need a few seconds to start up if the instance was previously paused :rocket:
