package zlc.season.practicalrecyclerview;

import android.view.View;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/26
 * Time: 15:05
 * FIXME
 */
interface ConfigureView {
    void configureEmptyView(View emptyView);

    void configureErrorView(View errorView);

    void configureLoadingView(View loadingView);

    void configureLoadMoreView(View loadMoreView);

    void configureNoMoreView(View noMoreView);

    void configureLoadMoreErrorView(View loadMoreErrorView);
}
