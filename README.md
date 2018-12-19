Using AWS Java SDK for Amazon S3 in Katalon Studio --- how to resolve external dependencies with Gradle
===========

## What is this?

This is a small [Katalon Studio](https://www.katalon.com/) project for demonstration purpose.
You can download the zip from the [Releases] page, unzip it, and run in Katalon Studio on your PC.

This project is developed with Katalon Studio version 5.9.1.

This project is made to show you
1. how to resolve the external dependencies (a lot of jar files in the `&lt;projectDir&gt;/Drivers` directory) in a automated way using [Gradle](https://docs.gradle.org/current/userguide/introduction_dependency_management.html).
2. how to call *AWS Java SDK for Amazon S3* in a test case in Katalon Studio.

## Problems to solve

### (1) I want to use Amazon S3 in my test cases

My [*Visual Testing in Katalon Studio*](https://forum.katalon.com/t/visual-testing-in-katalon-studio/13361) project enabled me to do visual regression testing. It compares two environment of my AUT (the projecty environement and the development environment). This project is useful for me, but caused a secondary issue.

Driving it in a Continuous Integration system resulted huge number of screen shot files. Image files tends to be big. The local disk has got used near to 100%. I have to *manage* the accumulated screenshot files. One idea has come up to my mind. How about uploading screenshot files into Amazon S3?

S3 seems to be promising for me. With S3, I do not have to worry about the storage capacity, it's cheap, files older than 1 month will be automatically deleted, ... etc.

Therefore I want my Katalon Studio project calls *AWS Java SDK for S3* so that it can transport screenshot files to and from Amazon S3.

### (2) How to resolve external dependencies?

[AWS document](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3.html) provides enough information how to use *AWS Java SDK for S3* in my Java/Groovy codes. So I tried to write a test case in Groovy in Katalon Studio which calls AWS API for S3. I wanted it to list my S3Buckets in my AWS account. Soon I found a blocking problem.

Katalon Studio requires all of the external dependencies (jar files) put into the `&lt;projectDir/Drivers` directory. I know I need the `aws-java-sdk-core-1.11.470.jar` and `aws-java-sdk-s3-1.11.470.jar`. But not only these 2, there are a lot more dependencies (in fact 14 jars). Katalon Studio does not provide any dependency management.

A quote from [Gradle document](https://docs.gradle.org/current/userguide/introduction_dependency_management.html)
>What is dependency management?
>
>Software projects rarely work in isolation. In most cases, a project relies on reusable functionality in the form of libraries or is broken up into individual components to compose a modularized system. Dependency management is a technique for declaring, resolving and using dependencies required by the project in an automated fashion.




```
$ ls Drivers
katalon_generated_aws-java-sdk-core-1.11.470.jar
katalon_generated_aws-java-sdk-kms-1.11.470.jar
katalon_generated_aws-java-sdk-s3-1.11.470.jar
katalon_generated_commons-codec-1.10.jar
katalon_generated_commons-logging-1.2.jar
katalon_generated_httpclient-4.5.5.jar
katalon_generated_httpcore-4.4.9.jar
katalon_generated_ion-java-1.0.2.jar
katalon_generated_jackson-annotations-2.6.0.jar
katalon_generated_jackson-core-2.6.7.jar
katalon_generated_jackson-databind-2.6.7.2.jar
katalon_generated_jackson-dataformat-cbor-2.6.7.jar
katalon_generated_jmespath-java-1.11.470.jar
katalon_generated_joda-time-2.8.1.jar
```
