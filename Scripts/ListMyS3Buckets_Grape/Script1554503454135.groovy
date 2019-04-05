/**
 * https://forum.katalon.com/t/dependency-management/21509 raised an interesting issue:
 * >It would be very useful for Katalon Studio to provide an interface for proper 
 * dependency management. Considering that Katalon uses Groovy as the scripting language,
 * maybe katalon can expose Groovy's dependency management system - Grape.
 * 
 * kazurayam found the following sample code
 * https://stackoverflow.com/questions/20009993/groovy-how-does-grab-inclusion-differ-from-classpath-inclusion
 * 
 * Here kazuarayam tried using @Grab in a Test Case of Katalon Studio --- it does not work.
 */
@GrabConfig(systemClassLoader = true)
@Grab(group='com.amazonaws', module='aws-java-sdk-s3', version='1.11.470')





import com.amazonaws.ClientConfiguration
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * see "List Buckets" section in
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html
 */

final AmazonS3 s3 = AmazonS3ClientBuilder.
						standard().
						withClientConfiguration(getClientConfigurationWithProxy()).
						build();

List<Bucket> buckets = s3.listBuckets();
System.out.println("My Amazon S3 buckets are:");
for (Bucket b : buckets) {
	System.out.println("* " + b.getName());
}


/**
 * with Proxy setting
 *
 * @return
 */
ClientConfiguration getClientConfigurationWithProxy() {
	ClientConfiguration conf = new ClientConfiguration();
	conf.setProxyHost("172.24.2.10");
	conf.setProxyPort(8080);
	return conf;
}