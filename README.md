# **This project is in development**

# **Introduction**
This project consists of obtaining data on vehicle theft in the city of São Paulo and consolidating it in a counting and heat map, in order to show areas with a higher index of this type of crime.

# **General Architecture**

![alt text](https://github.com/markoshlima/crimes-map/blob/master/docs/Architecture.png?raw=true)

The product works with the concept of big data, the idea is to have several data entries, however, initially the data will be captured through the São Paulo Public Security Secretariat.
All data processing will be done through AWS EMR, and the architecture will be event oriented.
At the end, a broker using AWS Kinesis will distribute the mined and enriched information to three different sources. Sources that will be read by Business Intelligence, Analytics and Applications tools.

# **Pricing**

All resources were priced in AWS Calculator, the following link, the final price of this architecture.

[Click here for Pricing Project](https://calculator.aws/#/estimate?id=c4b24b6aa1bd06f978c5511db972974dc5e12115)
