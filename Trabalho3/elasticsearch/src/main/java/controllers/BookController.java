package controllers;

import com.alibaba.fastjson.JSON;
import models.Book;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ml.job.results.Bucket;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.support.ValuesSource;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.w3c.dom.ranges.Range;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookController {
    public void create(RestHighLevelClient client, Book book) throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id", book.getId())
                .field("title", book.getTitle())
                .field("publishYear", book.getPublishYear())
                .array("authors", book.getAuthors())
                .field("category", book.getCategory())
                .field("value", book.getValue())
                .endObject();
        IndexRequest indexRequest = new IndexRequest("books");
        indexRequest.source(builder);

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);

        if(DocWriteResponse.Result.CREATED==response.getResult())
            System.out.println("A new book was created");
        else
            System.out.println("Book was not created");
    }

    public void searchByTitle(RestHighLevelClient client, String fieldName, String value) throws IOException {
        System.out.println("Searching for results");
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName, value);
        searchSourceBuilder.query(matchQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Book> results =
                Arrays.stream(searchHits)
                        .map(hit -> {
                            Book bookJson = JSON.parseObject(hit.getSourceAsString(), Book.class);
                            bookJson.setTitle((char) 27 + "[35m"+bookJson.getTitle()+ (char) 27 +"[0m" );
                            return bookJson;
                        })
                        .collect(Collectors.toList());

        for (Book book : results) {
            System.out.println(book);
        }

        System.out.println("Found "+results.size()+" result(s)!");
    }

    public void searchByAuthor(RestHighLevelClient client, String fieldName, String value) throws IOException {
        System.out.println("Searching for results");
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName, value);
        searchSourceBuilder.query(matchQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        List<Book> results =
                Arrays.stream(searchHits)
                        .map(hit -> {
                            Book bookJson = JSON.parseObject(hit.getSourceAsString(), Book.class);
                            return bookJson;
                        })
                        .collect(Collectors.toList());

        for (Book book : results) {
            System.out.println(book);
        }

        System.out.println("Found "+results.size()+" result(s)!");
    }
    
    public void HowManyBooksByCategory(RestHighLevelClient client) throws IOException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("category"));

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("categories")
                .field("category.keyword");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.aggregation(aggregationBuilder);

        SearchRequest request = new SearchRequest()
                .indices("books")
                .allowPartialSearchResults(false)
                .source(sourceBuilder)
                .requestCache(true);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregations aggregations = response.getAggregations();
        for(Aggregation agg:aggregations ){
            List<Terms.Bucket> buckets = (List<Terms.Bucket>) ((Terms) agg).getBuckets();
            for(Terms.Bucket bucket: buckets) {
                System.out.println(bucket.getKeyAsString() +" - "+bucket.getDocCount());
            }
        }
    }

    public void getAveragePriceByCategory(RestHighLevelClient client) throws IOException {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.rangeQuery("category"));

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("categories_avg_price")
                .field("category.keyword");
        aggregationBuilder.subAggregation(AggregationBuilders.avg("avg_price")
                .field("value"));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.aggregation(aggregationBuilder);

        SearchRequest request = new SearchRequest()
                .indices("books")
                .allowPartialSearchResults(false)
                .source(sourceBuilder)
                .requestCache(true);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregations aggregations = response.getAggregations();
        for(Aggregation agg:aggregations ){
            List<Terms.Bucket> buckets = (List<Terms.Bucket>) ((Terms) agg).getBuckets();
            for(Terms.Bucket bucket: buckets) {
                Avg averagePrice = bucket.getAggregations().get("avg_price");
                System.out.print(bucket.getKeyAsString() +" - R$");
                System.out.printf("%.2f", averagePrice.getValue());
                System.out.print("\n");
            }
        }

    }
}
