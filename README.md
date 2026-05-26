Software Development Project 2026

# CaseCraft — Software Modeling Tool

CaseCraft is a web-based software engineering tool designed to help users create, organize and manage software modeling artifacts in a clean and structured workspace.

The application allows users to manage projects, define actors, write use cases, create CRC cards and generate diagram scripts using PlantUML or Nomnoml. It is built as a full-stack Spring Boot application with Thymeleaf templates, Spring Security authentication and a clean responsive user interface.

---

## Overview

CaseCraft focuses on supporting the early stages of software design and analysis. Instead of keeping software engineering artifacts scattered across separate documents, the application provides a central workspace where each project can contain:

- Actors
- Use cases
- CRC cards
- Generated use case diagram scripts
- Generated class diagram scripts

The main goal of the project is to provide a simple and practical tool for organizing software requirements and design elements while also demonstrating clean software architecture, authentication, CRUD operations and diagram generation strategies.

---

## Features

### User Authentication

CaseCraft supports user registration, login and logout using Spring Security.

Each user has access only to their own projects.

Main authentication features:

- User registration
- Secure login
- Logout functionality
- Profile update support
- Protected project pages

---

### Project Management

Users can create and manage multiple software engineering projects.

Supported project operations:

- Create a new project
- View all user projects
- Open project details
- Edit project name and description
- Delete project
- Dashboard statistics for project-related data

Each project acts as a workspace that contains all related modeling artifacts.

---

### Dashboard Statistics

The main projects page includes dashboard statistic cards showing:

- Total projects
- Total use cases
- Total CRC cards
- Total actors

This gives the user a quick overview of their workspace.

---

### Project Cards

Projects are displayed as modern project cards instead of a plain table.

Each card includes:

- Project name
- Project description
- Open project action
- Edit project action
- Delete project action

This provides a cleaner and more dashboard-like user experience.

---

### Actors

Inside each project, users can define actors that interact with the system.

Supported actor operations:

- Add actor
- View project actors
- Delete actor

Actors are later used in use case diagram generation.

---

### Use Cases

Users can define use cases for each project.

Each use case can contain:

- Name
- Preconditions
- Main flow
- Alternative flow
- Postconditions

Supported use case operations:

- Create use case
- Edit use case
- Delete use case
- View use cases inside the project detail page

Use cases are used as input for generated use case diagram scripts.

---

### CRC Cards

CaseCraft supports CRC card creation for class-based analysis.

Each CRC card can contain:

- Class name
- Responsibilities
- Collaborations

Supported CRC card operations:

- Create CRC card
- Edit CRC card
- Delete CRC card
- Link CRC cards with use cases

CRC cards are used as input for class diagram generation.

---

### Diagram Generation

CaseCraft can generate diagram scripts from project data.

Supported diagram types:

- Use Case Diagram
- Class Diagram

Supported diagram tools:

- PlantUML
- Nomnoml

The generated script can be copied and pasted into the corresponding diagram tool.

This allows users to quickly transform stored project data into visual diagrams.

---

### Tab-Based Project Detail Page

The project detail page uses a tab-based layout to organize project content.

Tabs include:

- Actors
- Use Cases
- CRC Cards
- Diagrams

This avoids long vertical pages and makes the project detail area easier to navigate.

---

### Toast Notifications

The application uses toast notifications to provide user feedback after important actions.

Examples:

- Project created successfully
- Project updated successfully
- Project deleted
- Use case created
- CRC card updated
- Actor deleted
- Invalid login credentials

This improves the overall user experience by replacing plain page messages with modern notifications.

---

### Empty States

CaseCraft includes empty states for pages or sections that do not yet contain data.

For example, when a user has no projects yet, the interface shows a clear message instead of an empty table.

This makes the application feel more complete and user-friendly.

---

## Tech Stack

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

### Frontend

- Thymeleaf
- HTML5
- CSS3
- JavaScript

### Diagram Generation

- PlantUML script generation
- Nomnoml script generation

### Build Tool

- Maven

---

## Application Architecture

The application follows a layered architecture.

```text
Controllers
    ↓
Services
    ↓
Repositories
    ↓
Domain Entities
```
