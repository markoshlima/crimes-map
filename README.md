# This project is in development

# Introduction
This project consists of obtaining data on vehicle theft in the city of São Paulo and consolidating it in a counting and heat map, in order to show areas with a higher index of this type of crime.

#General Architecture

[image]

The product works with the concept of big data, the idea is to have several data entries, however, initially the data will be captured through the São Paulo Public Security Secretariat.
All data processing will be done through AWS EMR, and the architecture will be event oriented.
At the end, a broker using AWS Kinesis will distribute the mined and enriched information to three different sources. Sources that will be read by Business Intelligence, Analytics and Applications tools.