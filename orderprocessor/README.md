# Order processor project

## Description

Changes:
* In pom.xml: removed dependency to org.jboss.fuse.bom, could not build otherwise
* In pom-xml: added version numbers for artifacts, could not build otherwise
* Changed logging framework to Apache log4j, could not make logging to file to work otherwise
* Added Route2: Splits input message with orders/order structure into separate order in output
* Added Route3: Splits input message with orders/order structure into separate order in output and creates separate files for each order
