
# Energy and Risk analysis tool for goal models

This project is the implementation tool of my thesis project. It provides support to the propagation of energy and risk data of security measures and workflow patterns over a goal model representing security policies.


## Requirements

The repository can be cloned in your IDE, but before being able to use it some requirements need to be met:

- Java JDK 20 can be downloaded at https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html
- json-simple jar can be downloaded at https://code.google.com/archive/p/json-simple/downloads
- guava jar can be downloaded at https://repo1.maven.org/maven2/com/google/guava/guava/32.1.2-jre/

Before to proceed configuring the IDE, the downloaded JDK needs to be installed on the system. Installation support can be found at https://docs.oracle.com/en/java/javase/20/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA.

During the creation of the project select the jdk-20 just installed as JRE.

To import the two libraries, navigate to the project settings and look for libraries: here you should be able to find JARs by specifing their location on your drive.

Once the setup has been performed, you can clone this repository into your project.


## Importing the goal model

The goal model could be expressed via the online tool piStar that can be found at https://www.cin.ufpe.br/~jhcp/pistar/.

The model can then be exported in a .txt file. The file extension can be changed in .json and its structure must be modified by hand to be coherent with the goal models present in the *Goal models* project folder. The overall structure is the following:

- List of workflow patterns (wfp)
- List of security measures (sm)
- List of assets
- List of actors with security goal specified in their *nodes* list
- List of relationships (protect from goal->asset; enforce from goal->sm/wfp; is-a from sm->sm; part-of from asset/asset)
- List of links (carefull that piStar semantics is from child to parent)

The obtained JSON file can be saved in the *Goal models* directory and in order to be imported in the tool the path must be specified in line 36 of *JsonParser* class inside the *gmConverter* directory.

```
String filePath = "Goal models/goal_model_name.json";
```
 
## Importing risk data

Risk data can be imported in a similar way by creating a .json file organized as follows:

- List of wfp characterized by their id and Risk Mitigation Impact (rmi)
- List of sm characterized by their id and Risk Mitigation Impact (rmi)
- List of assets characterized by their id and Inherent Risk (ir)

Examples can be found in folder *Risk data*

The file can be saved in *Risk data* folder and then line 31 of class *JsonRiskParser* should be modified accordingly to the name given to the file

```
String filePath = "Risk data/risk_data_name.json";
```
## Importing simulation xml

The energy data outputted from the simulation software chosen should be organized in a .xml file containing a list of tasks, each comprehending at least the following data:
- Energy consumption of task
- Security measure linked to the task

An example of xm files can be found in *Plant Simulation XML* folder. The structure of a task should thus look like this:

```
<task xmlns="Task_1">
		<Task_name>Inspect_customer_data</Task_name>
		<Description>The description of the task goes here</Description>
		<Energy_consumption>2.78333333333332 kWh</Energy_consumption>
		<Security_measure>OPA for data</Security_measure>
```

The file is to be saved in the *Plant Simulation XML* folder and line 32 of class *XmlParser* should be changed to import the right xml file

```
String filePath = "Plant Simulation XML/energy_xml_name.xml";
```
## Knowledge Base

The Knowledge Base (KB) can be directly modified in the *Client* by hand or by deserializing a previously serialized Table. 

Entries of the KB have the following syntax:

```
knowledgeBase.putEntry("Security measure name", "Workflow Pattern name", "Description of the conflict");
```
## Example run

Finally the tool can be runned. First, a graphical representation of the goal model is made:

```
IT department
|__ Standards compliance (RR:0.0,EN:0.0)
    |-- ISO/IEC 27001 compliance (RR:0.0,EN:0.0)
    |   |-- Access control provisioned (RR:0.0,EN:0.0)
    |   |__ Confidentiality of data (RR:0.0,EN:0.0)
    |__ ISO/IEC 27017 compliance (RR:0.0,EN:0.0)

Company
|__ IT security and data protection (RR:0.0,EN:0.0)
    |-- Prevent impacts (RR:0.0,EN:0.0)
    |   |-- Prevent legal impacts (RR:0.0,EN:0.0)
    |   |__ Prevent image impacts (RR:0.0,EN:0.0)
    |-- Avoid modifications after deployment (RR:0.0,EN:0.0)
    |__ Compliance (RR:0.0,EN:0.0)
        |-- Norms compliance (RR:0.0,EN:0.0)
        |   |-- GDPR compliance (RR:0.0,EN:0.0)
        |   |__ Digital PA laws (RR:0.0,EN:0.0)
        |__ Standard compliance (RR:0.0,EN:0.0)
```

Then the user is asked which wfp or sm to exclude from the propagation together with which risk propagation strategy to adopt. Finally the goal model is represented with goals enriched with risk and energy data. Furthermore some analysis related to those merics are displayed too.

```
IT department
|__ Standards compliance (RR:1.25,EN:8.35)
    |-- ISO/IEC 27001 compliance (RR:2.5,EN:8.35)
    |   |-- Access control provisioned (RR:0.0,EN:2.7833333)
    |   |__ Confidentiality of data (RR:5.0,EN:5.5666666)
    |__ ISO/IEC 27017 compliance (RR:0.0,EN:0.0)

Company
|__ IT security and data protection (RR:0.5833333,EN:5.5666666)
    |-- Prevent impacts (RR:1.0,EN:2.7833333)
    |   |-- Prevent legal impacts (RR:2.0,EN:2.7833333)
    |   |__ Prevent image impacts (RR:0.0,EN:0.0)
    |-- Avoid modifications after deployment (RR:0.0,EN:0.0)
    |__ Compliance (RR:0.75,EN:2.7833333)
        |-- Norms compliance (RR:1.5,EN:2.7833333)
        |   |-- GDPR compliance (RR:3.0,EN:2.7833333)
        |   |__ Digital PA laws (RR:0.0,EN:0.0)
        |__ Standard compliance (RR:0.0,EN:0.0)
```
## License

Shield: [![CC BY-NC-SA 4.0][cc-by-nc-sa-shield]][cc-by-nc-sa]

This work is licensed under a
[Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License][cc-by-nc-sa].

[![CC BY-NC-SA 4.0][cc-by-nc-sa-image]][cc-by-nc-sa]

[cc-by-nc-sa]: http://creativecommons.org/licenses/by-nc-sa/4.0/
[cc-by-nc-sa-image]: https://licensebuttons.net/l/by-nc-sa/4.0/88x31.png
[cc-by-nc-sa-shield]: https://img.shields.io/badge/License-CC%20BY--NC--SA%204.0-lightgrey.svg

