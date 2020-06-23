package com.anilemrah.dolap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

/**
 * DynamoDB config class
 * 
 * @author Anil Emrah
 *
 */
@Configuration
public class DynamoDBConfiguration {
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(
				// This part will not be uploaded to the Github
				// Keys will be replaced by XXX
				new BasicAWSCredentials("XXX", "XXX")))
				.withRegion(Regions.EU_CENTRAL_1).build();
		return new DynamoDBMapper(client, DynamoDBMapperConfig.DEFAULT);
	}
}