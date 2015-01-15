package zlogger.logic.models;

import java.util.List;

public class PagedList<T> {
    private List<T> items;
    private Long totalItemsCount;

    private int pageNumber;
    private int pageSize;
    private long totalPages;

    public PagedList(List<T> items, Long totalItemsCount, int pageNumber, int pageSize) {
        this.items = items;
        this.totalItemsCount = totalItemsCount;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.totalPages = totalItemsCount / pageSize;

        if (totalItemsCount % pageSize > 0) {
            this.totalPages++;
        }
    }

    public boolean hasNextPage() {
        return pageNumber < totalPages;
    }

    public boolean hasPreviousPage() {
        return pageNumber > 1;
    }

    public List<T> getItems() {
        return items;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
