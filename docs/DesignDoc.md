# Table of Contents
- [PROJECT Design Documentation](#project-design-documentation)
  * [Executive Summary](#executive-summary)
    + [Purpose](#purpose)
    + [Glossary and Acronyms](#glossary-and-acronyms)
  * [Requirements](#requirements)
    + [Definition of MVP](#definition-of-mvp)
    + [MVP Features](#mvp-features)
    + [Roadmap of Enhancements](#roadmap-of-enhancements)
  * [Application Domain](#application-domain)
    + [Overview of Major Domain Areas](#overview-of-major-domain-areas)
    + [Details of each Domain Area](#details-of-each-domain-area)
  * [Architecture](#architecture)
    + [Summary](#summary)
    + [Overview of User Interface](#overview-of-user-interface)
    + [Tier X](#tier-x)
  * [Sub-system X](#sub-system-x)
    + [Purpose of the sub-system](#purpose-of-the-sub-system)
    + [Static models](#static-models)
    + [Dynamic models](#dynamic-models)

# PROJECT Design Documentation

## Executive Summary

This is a summary of the project.

### Purpose
This   software   design   document   describes   the   architecture   and   system   design   of   the   WebCheckers webapp   game.

### Glossary and Acronyms

| Term | Definition |
|------|------------|
| SOA | Service Oriented Architecture|
|------|------------|
| AI | Artificial Intelligence |
|------|------------|
| DAO | Data Access Object |


## Requirements

This section describes the features of the application.

### Definition of MVP
MVP stands for Minimum Viable Product. MVP are all the stories required to be completed in the first release. In other words, MVP is the product with enough features to satisfy customers and to provide feedback for future developments.

### MVP Features
The   general   functionality   of   the   project   is   to   provide   the   user   with   a   web   interface   that   allows users   to   play   the   game   of   checkers.   Standard   features   will   include:
* New   player   sign-up
* Existing   player   sign-in
* Player   sign-out
● Asynchronous   checkers   gameplay   including:
○ Play   against   another   human
○ Checker   capture
○ Kings
○ Game   options   including
■ Back   up   one   move
■ Reset   turn
■ Submit   turn
■ Quit   after   a   game
■ Resign   during   a   current   game

### Roadmap of Enhancements
> Provide a list of top-level features in order you plan to consider them.


## Application Domain

This section describes the application domain.

### Overview of Major Domain Areas
> Provide a high-level overview of the 

### Details of each Domain Area
> If necessary, high-light certain areas of the Domain model that have a focused purpose.  Create textual narrative that describes the purpose and how that relates to the associated domain model.



## Architecture

This section describes the application architecture.

### Summary
> Provide a brief summary of the architecture.  Also provide one or two models (diagrams) that describe the architecture.  Hint: review the Architecture lecture slides for ideas.

### Overview of User Interface
> Provide a summary of the application's user interface.
> This includes the UI state model.

### Tier X
> Provide a summary of each tier of your architecture.  Thus replicate this heading for each tier.
> In each section describe the types of components in the tier and describe their responsibilities.


## Sub-system X
> Provide a section for each major sub-system within the tiers of the architecture.  Replace 'X' with the name of the sub-system.
> A sub-system would exist within one of the application tiers and is a group of components cooperating on a significant purpose within the application.  For example, in WebCheckers all of the UI Controller components for the Game view would be its own sub-system.

This section describes the detail design of sub-system X.

### Purpose of the sub-system
> Provide a summary of the purpose of this sub-system.

### Static models
> Provide one or more static models (UML class or object diagrams) with some details such as critical attributes and methods.  If the sub-system is large (over 10 classes) then consider decomposing into multiple, smaller, more focused diagrams.

### Dynamic models
> Provide any dynamic model, such as state and sequence diagrams, as is relevant to a particularly significant user story.
> For example, in WebCheckers you might create a sequence diagram of the `POST /validateMove` HTTP request processing or you might use a state diagram if the Game component uses a state machine to manage the game.
