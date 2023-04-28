import com.amazonaws.ClientConfiguration
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * see "List Buckets" section in
 * https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-buckets.html
 */

final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
						.withRegion("ap-northeast-1")
						// withClientConfiguration(getClientConfigurationWithProxy()).
						.build();

List<Bucket> buckets = s3.listBuckets();
System.out.println("My Amazon S3 buckets are:");
for (Bucket b : buckets) {
	System.out.println("* " + b.getName());
}


/**
 * settings to go through kazurayam's Organizational Proxy
 * 
 * @return
 */
ClientConfiguration getClientConfigurationWithProxy() {
	ClientConfiguration conf = new ClientConfiguration();
	conf.setProxyHost("172.24.2.10");
	conf.setProxyPort(8080);
	return conf;
}