package com.imorochi.chasqui.application.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class SearchCustom {

    private SearchCustom() {}

    public static SearchRequest buildSearchRequest(final String indexName,
                                                   final SearchRequestDTO dto) {
        try {
            final int page = dto.getPage();
            final int size = dto.getSize();
            final int from = page <= 0 ? 0 : page * size;

            SearchSourceBuilder builder = new SearchSourceBuilder()
                    .from(from)
                    .size(size)
                    .postFilter(getQueryBuilder(dto));

            if (dto.getSortBy() != null) {
                builder = builder.sort(
                        dto.getSortBy(),
                        dto.getOrder() != null ? dto.getOrder() : SortOrder.ASC
                );
            }

            final SearchRequest request = new SearchRequest(indexName);
            request.source(builder);

            return request;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static QueryBuilder getQueryBuilder(final SearchRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        final List<String> fields = dto.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        if (fields.size() > 1) {
            final MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(dto.getSearchTerm())
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.AND);

            fields.forEach(queryBuilder::field);

            return queryBuilder;
        }

        return fields.stream()
                .findFirst()
                .map(field ->
                        QueryBuilders.matchQuery(field, dto.getSearchTerm())
                                .operator(Operator.AND))
                .orElse(null);
    }

}
