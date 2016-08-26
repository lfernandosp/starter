package com.fernandopereira.starterapp.view;

import com.fernandopereira.starterapp.model.Foo;

import java.util.List;

public interface PaginatedView {

    void refresh(List<Foo> freshItems);

    void appendNextPage(List<Foo> newItems);

    void handleError(Throwable throwable);

    void hideLoadingIndicator();

    void showItemDetails(String itemId);

    interface ItemActionHandler {
        void onItemTapped(String itemId);
    }
}

